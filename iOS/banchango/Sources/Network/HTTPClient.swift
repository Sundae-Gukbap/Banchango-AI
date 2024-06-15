//
//  HTTPClient.swift
//  banchango
//
//  Created by KimMinSeok on 6/15/24.
//

import Foundation

enum NetworkError: Error {
    case badURL
    case noData
    case decodingError
}

class HTTPClient {
    func getRecipe(completion: @escaping (Result<Recipe, NetworkError>) -> Void) {
        guard let url = URL.recipeRecommendURL(1) else {
            return completion(.failure(.badURL))    
        }
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data, error == nil else {
                return completion(.failure(.noData))
            }
            guard let recipe = try? JSONDecoder().decode(Recipe.self, from: data) else {
                return completion(.failure(.decodingError))
            }
            completion(.success(recipe))
        }.resume()
        
    }
}
