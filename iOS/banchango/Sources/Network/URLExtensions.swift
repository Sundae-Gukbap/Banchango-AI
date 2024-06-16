//
//  URLExtensions.swift
//  banchango
//
//  Created by KimMinSeok on 6/15/24.
//

import Foundation

extension URL {

    static func recipeRecommendURL(_ userId: Int) -> URL? {
        return URL(string: Config.baseURL + Config.recipeRecommedEndPoint + String(userId))
    }
    
    static func recipeDetailURL(userId: Int, recipeId: Int) -> URL? {
        return URL(string: Config.baseURL + Config.recipeDetailEndPoint + String(userId) + "/" + String(recipeId))
    }
    
}
