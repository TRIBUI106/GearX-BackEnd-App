package com.gearx.feature.category.dto.response;

import com.gearx.common.model.BaseEntity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse extends BaseEntity {

    Integer categoryId;
    String name;
    String description;
}
