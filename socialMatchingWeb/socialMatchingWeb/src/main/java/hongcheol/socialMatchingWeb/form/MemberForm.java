package hongcheol.socialMatchingWeb.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "사용할 이름을 입력해주세요")
    private String name;
}
