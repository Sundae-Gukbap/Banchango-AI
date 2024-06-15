//
//  SwiftUIView.swift
//  banchango
//
//  Created by KimMinSeok on 6/3/24.
//

import SwiftUI

struct RecipeRecommendView: View {
    @ObservedObject private var recipeRecommendVM: RecipeRecommendViewModel
    
    init() {
        self.recipeRecommendVM = RecipeRecommendViewModel()
        recipeRecommendVM.getRecipeRecommend()
    }
    
    let colors:[Color] = [.purple, .pink, .orange]
    
    var body: some View {
        GeometryReader { proxy in
            ScrollView(.vertical) {
                VStack(spacing: 0) {
                    ForEach(recipeRecommendVM.recipes.indices, id: \.self) { index in
                        RecipeCardView(recipe: recipeRecommendVM.recipes[index])
                            .frame(width: proxy.size.width, height: proxy.size.height)
                    }
                }
            }
        }.onAppear() {
            UIScrollView.appearance().isPagingEnabled = true
        }
    }
}


struct RecipeCardView: View {
    let recipe: Recipe
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 20)
                .fill(Color.clear)
                .background(
                    AsyncImage(url: URL(string: recipe.image)) { image in
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: 300, height: 550)
                    } placeholder: {
                        Color.gray.opacity(0.3)
                    }
                    .clipShape(RoundedRectangle(cornerRadius: 20))
                )
                .frame(width: 300, height: 550)
                .overlay(
                    VStack {
                        Text(recipe.name)
                            .font(.largeTitle)
                            .foregroundColor(.black)
                        Spacer()
                        Text(recipe.introduction)
                            .font(.caption)
                            .foregroundColor(.gray)
                            .multilineTextAlignment(.center)
                    }
                    .padding()
                )
        }
    }
}

#Preview {
    RecipeRecommendView()
}
