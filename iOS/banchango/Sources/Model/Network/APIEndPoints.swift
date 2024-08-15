//
//  APIEndPoint.swift
//  banchango
//
//  Created by KimMinSeok on 6/16/24.
//

import Foundation

enum NetworkError: Error {
    case badURL
    case noData
    case decodingError
}

struct HTTPClient {
    static func getRecommendRecipe(userId: Int, completion: @escaping (Result<RecommendRecipe, NetworkError>) -> Void) {
        guard let url = URL.recipeRecommendURL(userId) else {
            return completion(.failure(.badURL))
        }
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data, error == nil else {
                return completion(.failure(.noData))
            }
            guard let recommendRecipe = try? JSONDecoder().decode(RecommendRecipe.self, from: data) else {
                return completion(.failure(.decodingError))
            }
            completion(.success(recommendRecipe))
        }.resume()
    }
    
    static func getDetailRecipe(userId: Int, recipeId:Int, completion: @escaping (Result<DetailRecipe, NetworkError>) -> Void) {
        guard let url = URL.recipeDetailURL(userId: userId, recipeId: recipeId) else {
            return completion(.failure(.badURL))
        }
        URLSession.shared.dataTask(with: url) { data, response, error in
            guard let data = data, error == nil else {
                return completion(.failure(.noData))
            }
            guard let detailRecipe = try? JSONDecoder().decode(DetailRecipe.self, from: data) else {
                return completion(.failure(.decodingError))
            }
            completion(.success(detailRecipe))
        }.resume()
    }
}
