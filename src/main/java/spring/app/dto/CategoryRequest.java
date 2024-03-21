package spring.app.dto;

import lombok.Builder;

@Builder
public record CategoryRequest (int id,String title,String description){
}
