package com.rest.models.pet.requests;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class PetToStoreRequest {

    private Integer id;
    private CategoryRequest category;
    private String name;
    private List<String> photoUrls;
    private List<TagsRequest> tags;
    private String status;
}
