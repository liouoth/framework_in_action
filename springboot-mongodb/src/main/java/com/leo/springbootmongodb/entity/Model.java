package com.leo.springbootmongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "model")
public class Model {
    @Id
    private String id;

    private Long entity_id;

    private String SF36Unicode;

    private String eid;

    private String vname;

    private List<Property> properties;

    private Integer modelId;

    private Integer version;
}