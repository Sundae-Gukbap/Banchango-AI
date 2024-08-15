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
        AsyncImage(url: URL(string: recipeDetailVM.recipes?.image ?? "")) { image in
            image
                .resizable()
                .scaledToFill()
                .frame(width: .infinity, height: .infinity)
        } placeholder: {
            Color.gray.opacity(0.3)
        }
            .clipShape(RoundedRectangle(cornerRadius: 20))
        Text(String(recipeDetailVM.recipes?.name ?? ""))
        Text(String(recipeDetailVM.recipes?.introduction ?? ""))
    }
}

#Preview {
    RecipeDetailView(userId: 1, recipeId:6988019)
}
