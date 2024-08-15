//
//  SwiftUIView.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import SwiftUI

struct RecipeRecommendView: View {
    @ObservedObject private var recipeRecommendVM: RecipeRecommendViewModel
    
    let userId = 1
    
    init() {
        self.recipeRecommendVM = RecipeRecommendViewModel()
        recipeRecommendVM.getRecipeRecommend(userId: userId)
    }
    
    var body: some View {
        GeometryReader { proxy in
            ScrollView(.vertical) {
                VStack(spacing: 0) {
                    ForEach(recipeRecommendVM.recipes.indices, id: \.self) { index in
                        RecipeCardView(userId: userId, recipe: recipeRecommendVM.recipes[index])
                            .frame(width: proxy.size.width, height: 226)
                    }
                }
            }
        }.onAppear() {
            UIScrollView.appearance().isPagingEnabled = true
        }
    }
}
import SwiftUI

struct RecipeCardView: View {
    let userId: Int
    let recipe: RecommendRecipe
    @State private var showDetailView = false
    
    func haveNeedIngr(have: [String], need: [String]) -> String {
        let haveCount = have.count
        let needCount = need.count
        
        return "\(haveCount)/\(needCount+haveCount)"
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            // 상단 이미지
            ZStack(alignment: .topTrailing) {
                AsyncImage(url: URL(string: recipe.image)) { image in
                    image
                        .resizable()
                        .scaledToFill()
                        .frame(height: 150)
                        .clipped()
                } placeholder: {
                    Color.gray.opacity(0.3)
                        .frame(height: 150)
                }
                
                // 이미지 우측 상단의 아이콘
                Image(systemName: "heart.fill")
                    .resizable()
                    .frame(width: 30, height: 30)
                    .padding()
                    .background(Color.white.opacity(0.7))
                    .clipShape(Circle())
                    .padding([.top, .trailing], 10)
            }
            
            // 텍스트 정보
            VStack(alignment: .leading, spacing: 10) {
                Text(recipe.name)
                    .font(.custom("Inter-Bold", size: 20))
                    .foregroundColor(.black)
                    .lineLimit(2)
                
                HStack {
                    Text(haveNeedIngr(have: recipe.have, need: recipe.need))
                        .font(.custom("Inter-Bold", size: 14))
                        .foregroundColor(.orange)
                    
                    Spacer()
                    
                    // 추가 정보들
                    HStack(spacing: 10) {
                        Image(systemName: "star.fill")
                            .foregroundColor(.orange)
                        Text(recipe.difficulty)
                            .font(.custom("Inter-Bold", size: 14))
                            .foregroundColor(.gray)
                        
                        Text("\(recipe.servings)인분")
                            .font(.custom("Inter-Bold", size: 14))
                            .foregroundColor(.gray)
                        
                        HStack(spacing: 2) {
                            Image(systemName: "clock")
                            Text("\(recipe.cookingTime)분")
                        }
                        .font(.custom("Inter-Bold", size: 14))
                        .foregroundColor(.gray)
                    }
                }
            }
            .padding([.leading, .bottom, .trailing], 15)
            .background(Color.white)
        }
        .background(
            RoundedRectangle(cornerRadius: 20)
                .stroke(Color.gray.opacity(0.2), lineWidth: 1)
                .shadow(color: Color.black.opacity(0.1), radius: 5, x: 0, y: 2)
        )
        .onTapGesture {
            showDetailView.toggle()
        }
        .sheet(isPresented: $showDetailView) {
            RecipeDetailView(userId: userId, recipeId: recipe.id)
        }
    }
}


#Preview {
    RecipeRecommendView()
}

