package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.MarsAPIRequest;

public interface RequestRepository extends JpaRepository<MarsAPIRequest, Long>{
	Optional<MarsAPIRequest> findById(Long id);
}
