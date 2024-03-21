package spring.app.dto;

import lombok.Builder;

@Builder
    public record ProductResponse(Integer id,String title,String description,float price, String imageUrl){
}
