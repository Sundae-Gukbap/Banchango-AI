//
//  RecipeDetailView.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import SwiftUI

struct RecipeDetailView: View {
    
    let userId: Int
    let recipeId: Int
    
    @ObservedObject private var recipeDetailVM: RecipeDetailViewModel
    
    init(userId: Int, recipeId: Int) {
        self.recipeId = recipeId
        self.userId = userId
        self.recipeDetailVM = RecipeDetailViewModel()
        recipeDetailVM.getRecipeDetail(userId: userId, recipeId : recipeId)
    }
    
    
    var body: some View {
        Text(String(recipeDetailVM.recipes!.id))
        Text(String(recipeDetailVM.recipes!.name))
        Text(String(recipeDetailVM.recipes!.introduction))
        Text(String(recipeId))
    }
}

#Preview {
    RecipeDetailView(userId: 1, recipeId:6952728)
}
