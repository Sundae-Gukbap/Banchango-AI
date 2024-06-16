//
//  RecipeDetailView.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import SwiftUI

struct RecipeDetailView: View {
    
    let userId:Int
    let recipeId:Int
    
    @ObservedObject private var recipeRecommendVM: RecipeRecommendViewModel
    
    init(recipeId: Int, userId: Int) {
        self.recipeId = recipeId
        self.userId = userId
        self.recipeRecommendVM = RecipeRecommendViewModel()
//        recipeRecommendVM.getRecipeRecommend(userId:, recipeId : recipeId)
    }
    
    
    var body: some View {
        Text(String(userId))
        Text(String(recipeId))
    }
}

#Preview {
    RecipeDetailView(recipeId:6952728, userId: 1)
}
