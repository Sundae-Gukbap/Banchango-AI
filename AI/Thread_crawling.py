import requests, json, csv, sys
from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry
from concurrent.futures import ThreadPoolExecutor, as_completed
from tqdm import tqdm
from bs4 import BeautifulSoup
import warnings

warnings.filterwarnings("ignore")

n = 0

with open('result.txt', 'r') as f:
    for _ in range(n):
        next(f)
    data = f.read().splitlines()
print(n) 
# -------------------------------
# 재시도 전략(Retry Strategy) 설정
# -------------------------------
retries = Retry(
    total=10,               # 최대 재시도 횟수: 10번
    backoff_factor=1,       # 재시도 사이의 지연 시간 (백오프 인자)
    status_forcelist=[500, 502, 503, 504, 104, 10054, 2]  # 재시도할 HTTP 상태 코드 리스트
)

# HTTPAdapter와 재시도 전략을 사용해 Session 객체 생성
session = requests.Session()
adapter = HTTPAdapter(max_retries=retries)
session.mount('http://', adapter)
session.mount('https://', adapter)

# HTTP 요청 시 사용할 headers 설정 (브라우저인 척 속이기 위한 User-Agent 등)
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3',
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7',
    'Accept-Language': 'ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7',
}

# CSV 파일에 기록할 필드(컬럼) 리스트
fields = ['index', 'name', 'best_name', 'rating', 'rating_count', 'author', 'author_type', 
          'totalTime', 'recipeYield', 'recipeLev', 'image', 'description', 
          'datePublished', 'recipeIngredient', 'recipeInstructions']

# -------------------------------------------
# 주어진 recipe_id에 해당하는 레시피 정보를 추출하는 함수
# -------------------------------------------
def process(recipe_id):
    # 레시피 URL 구성 (주어진 recipe_id를 이용)
    new_url = f'https://www.10000recipe.com/recipe/{recipe_id}'
    
    # URL에 HTTP GET 요청 (재시도 전략 적용, SSL 인증 무시)
    new_response = session.get(new_url, headers=headers, verify=False)
    
    # 응답 받은 HTML 텍스트를 파싱
    html = new_response.text
    soup = BeautifulSoup(html, 'html.parser')
    
    # JSON-LD 형식의 데이터(레시피 정보)를 찾음
    food_info = soup.find(attrs={'type':'application/ld+json'})
    
    # 레시피 난이도 정보를 포함하는 요소 찾기
    food_lev = soup.find(attrs={'class':'view2_summary_info3'})
    
    # 리뷰(평점) 관련 요소들을 모두 찾기
    reviews = soup.find_all(attrs={'class': 'media-heading'})
    
    # 베스트(추천) 요리명 정보를 찾기
    food_best_tit = soup.find(attrs={'style':'color:#74b243;'})
    
    total_rating = 0
    review_count = len(reviews)  # 리뷰 개수
    
    for review in reviews:
        star_images = review.find_all('img')
        total_rating += len(star_images)
    
    if review_count > 0:
        average_rating = total_rating / review_count
    else:
        average_rating = 0
    
    try:
        result = json.loads(food_info.text, strict=False)
        
        try:
            result['recipeIngredient'] = '|'.join(result['recipeIngredient'])
        except:
            food_ingr = soup.find('div', class_='cont_ingre').find('dd').text
            result['recipeIngredient'] = '|'.join(food_ingr.split(' ,'))
        
        instructions = [step['text'] for step in result['recipeInstructions']]
        result['recipeInstructions'] = '|'.join(instructions)
        
        result['image'] = '|'.join(result['image'])
        
        result['recipeLev'] = food_lev.text          # 난이도 정보
        result['index'] = recipe_id                    # recipe_id
        result['author_type'] = result['author']['@type'].lower()  # 작성자 타입 (소문자 변환)
        result['author'] = result['author']['name']    # 작성자 이름
        result['rating'] = average_rating              # 계산된 평균 평점
        result['rating_count'] = review_count          # 리뷰 개수
        result['best_name'] = food_best_tit.text       # 베스트 요리명
        
        row = {field: result.get(field, '') for field in fields}
        return row
        
    except (AttributeError, KeyError, json.JSONDecodeError):
        return recipe_id

# --------------------------------------------
# ThreadPoolExecutor를 사용하여 멀티스레딩으로 데이터 처리
# CSV 파일과 예외 발생 recipe_id를 기록할 텍스트 파일을 동시에 엽니다.
# --------------------------------------------
try:
    with ThreadPoolExecutor(max_workers=30) as executor, \
         open('recipe_5_30.csv', 'w', newline='', encoding='utf-8') as csvfile, \
         open('except.txt', 'w') as txtfile:
    
        writer = csv.DictWriter(csvfile, fieldnames=fields)
        
        if n == 0:
            writer.writeheader()
            n += 1 
        
        futures = [executor.submit(process, id) for id in data]
        
        for future in tqdm(as_completed(futures), total=len(data)):
            result = future.result()
            if isinstance(result, dict):
                writer.writerow(result)
            else:
                txtfile.write(str(result) + '\n')
                
except KeyboardInterrupt:
    sys.exit(0)
