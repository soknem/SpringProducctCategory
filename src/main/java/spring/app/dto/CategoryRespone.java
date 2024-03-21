package spring.app.dto;

import lombok.Builder;

@Builder
public record CategoryRespone (int id,String title,String description){
}
