//
//  RecipeCardView.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import SwiftUI

struct RecipeCardView: View {
    let userId: Int
    let recipe: RecommendRecipe
    func haveNeedIngr(have : [String], need : [String]) -> String {
        let haveCount = have.count
        let needCount = need.count
        
        return "\(haveCount)/\(needCount+haveCount)"
    }
    
    var body: some View {
        NavigationView{
            NavigationLink(destination: RecipeDetailView(recipeId:recipe.id, userId: userId)){
                ZStack {
                    RoundedRectangle(cornerRadius: 20)
                        .fill(Color.clear)
                        .frame(width: 300, height: 550)
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
                        .overlay(
                            VStack {
                                Text(recipe.name)
                                    .font(.custom("Inter-Bold", size: 30))
                                    .lineLimit(2)
                                    .foregroundColor(.white)
                                Spacer()
                                Text(haveNeedIngr(have: recipe.have, need:recipe.need))
                                    .font(.custom("Inter-Bold", size: 30))
                                    .foregroundColor(.white)
                            }
                                .padding()
                        )
                }
            }
        }
    }
}
