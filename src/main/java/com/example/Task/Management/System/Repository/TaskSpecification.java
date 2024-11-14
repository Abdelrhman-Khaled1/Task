package com.example.Task.Management.System.Repository;

import com.example.Task.Management.System.Entity.Task.Priority;
import com.example.Task.Management.System.Entity.Task.Status;
import com.example.Task.Management.System.Entity.Task.Task;
import com.example.Task.Management.System.Entity.User.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {

    public static Specification<Task> hasUser(User user) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user"), user);
    }

    public static Specification<Task> hasTitleContaining(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Task> hasDescriptionContaining(String description) {
        return (root, query, criteriaBuilder) ->
                description == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<Task> hasStatus(Status status) {
        return (root, query, criteriaBuilder) ->
                status == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> hasPriority(Priority priority) {
        return (root, query, criteriaBuilder) ->
                priority == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.equal(root.get("priority"), priority);
    }

    public static Specification<Task> hasDueDateBetween(LocalDateTime date1, LocalDateTime date2) {
        return (root, query, criteriaBuilder) -> {
            if (date1 == null && date2 == null) {
                return criteriaBuilder.conjunction();
            } else if (date1 != null && date2 != null) {
                return criteriaBuilder.between(root.get("dueDate"), date1, date2);
            } else if (date1 != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("dueDate"), date1);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("dueDate"), date2);
            }
        };
    }
}

