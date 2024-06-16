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
                            .frame(width: proxy.size.width, height: proxy.size.height)
                    }
                }
            }
        }.onAppear() {
            UIScrollView.appearance().isPagingEnabled = true
        }
    }
}

#Preview {
    RecipeRecommendView()
}
