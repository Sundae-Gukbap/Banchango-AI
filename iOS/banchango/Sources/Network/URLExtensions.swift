//
//  URLExtensions.swift
//  banchango
//
//  Created by KimMinSeok on 6/15/24.
//

import Foundation

extension URL {

    static func recipeRecommendURL(_ userId: Int) -> URL? {
        return URL(string: Constants.baseURL + Constants.recipeRecommedEndPoint + String(userId))
    }
    
    
}
