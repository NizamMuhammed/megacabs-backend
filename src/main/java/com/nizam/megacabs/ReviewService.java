package com.nizam.megacabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;


    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody,String cabNumber){
        Review review = reviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Cabs.class)
                .matching(Criteria.where("cabNumber").is(cabNumber))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;
    }
}
