package tech.springboot.productCommandService.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.springboot.productCommandService.model.SqsEvent;

public interface ProductEventRepository extends JpaRepository<SqsEvent, UUID> {

}
