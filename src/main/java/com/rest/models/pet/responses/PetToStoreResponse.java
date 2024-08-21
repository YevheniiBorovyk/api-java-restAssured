package com.rest.models.pet.responses;

import com.rest.models.pet.requests.CategoryRequest;
import com.rest.models.pet.requests.TagsRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class PetToStoreResponse {

    private Integer id;
    private CategoryRequest category;
    private String name;
    private List<String> photoUrls;
    private List<TagsRequest> tags;
    private String status;
}
