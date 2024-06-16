//
//  RecipeRecommendViewMode.swift
//  banchango
//
//  Created by KimMinSeok on 6/15/24.
//
import Foundation
import Combine

class RecipeRecommendViewModel: ObservableObject {
    @Published var recipes: [RecommendRecipe] = []
    
    private var cancellables: Set<AnyCancellable> = []
    
    func getRecipeRecommend(userId: Int) {
        guard let url = URL.recipeRecommendURL(userId) else {
            print("URL이 잘못되었습니다.") // URL이 잘못된 경우
            return
        }
        
        URLSession.shared.dataTaskPublisher(for: url)
            .map { $0.data }
            .decode(type: [RecommendRecipe].self, decoder: JSONDecoder())
            .receive(on: DispatchQueue.main)
            .sink(receiveCompletion: { completion in
                switch completion {
                case .failure(let error):
                    self.loadDummyData() // 네트워크 요청 실패 시 더미 데이터 로드
                    print("네트워크 오류: \(error)")
                case .finished:
                    print("추천 레시피 데이터 요청이 완료되었습니다.")
                }
            }, receiveValue: { [weak self] recipes in
                self?.recipes = recipes
            })
            .store(in: &cancellables)
    }
    
    private func loadDummyData() {
        if let url = Bundle.main.url(forResource: "RecommendRecipe", withExtension: "json"),
           let data = try? Data(contentsOf: url) {
            let decoder = JSONDecoder()
            if let dummyRecipes = try? decoder.decode([RecommendRecipe].self, from: data) {
                self.recipes = dummyRecipes
                print("더미 데이터를 로드했습니다.")
            } else {
                print("더미 데이터를 디코딩하는 데 실패했습니다.")
            }
        } else {
            print("더미 데이터 파일을 찾을 수 없습니다.")
        }
    }
    
}
