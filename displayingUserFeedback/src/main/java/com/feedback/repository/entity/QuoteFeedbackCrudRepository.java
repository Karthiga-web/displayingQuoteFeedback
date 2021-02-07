package com.feedback.repository.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteFeedbackCrudRepository extends CrudRepository<QuoteEntity, Integer> {
}
