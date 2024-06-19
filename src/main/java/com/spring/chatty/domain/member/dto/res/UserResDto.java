package com.spring.chatty.domain.member.dto.res;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResDto {
    private Long id;
    private String username;
    private String email;
}
