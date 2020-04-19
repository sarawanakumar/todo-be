package com.sarawanak.todobe.repository;

import com.sarawanak.todobe.model.Task;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskSpecificationBuilder {
    private final List<SearchCriteria> params;

    public TaskSpecificationBuilder() {
        this.params = new ArrayList<>();
    }

    public TaskSpecificationBuilder with(String key, Object value) {
        params.add(new SearchCriteria(key, value));
        return this;
    }

    public Specification<Task> build() {
        if (params.size() == 0) return null;

        List<Specification> specs = params.stream()
            .map(TaskSpecification::new)
            .collect(Collectors.toList());
        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }
}
