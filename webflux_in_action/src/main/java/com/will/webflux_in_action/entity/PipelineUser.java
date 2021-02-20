package com.will.webflux_in_action.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "pipelineUser")
public class PipelineUser {
    @Id
    private Long id;
    private String message;
}