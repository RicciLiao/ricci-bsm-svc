package ricciliao.bsm.component;

import ricciliao.bsm.common.BsmCodeList;
import ricciliao.bsm.pojo.dto.BsmCodeDetailDto;
import ricciliao.bsm.repository.BsmCodeDetailRepository;

import java.util.Optional;

public class BsmCodeListComponent {

    private final BsmCodeDetailRepository bsmCodeDetailRepository;

    public BsmCodeListComponent(BsmCodeDetailRepository bsmCodeDetailRepository) {
        this.bsmCodeDetailRepository = bsmCodeDetailRepository;
    }

    public String getVerificationForSignUp() {
        Optional<BsmCodeDetailDto> optional =
                bsmCodeDetailRepository.getByUniqueKey(
                        BsmCodeList.EMAIL_VERIFICATION,
                        BsmCodeList.BsmCodeEnum.EMAIL_VERIFICATION_FOR_SIGN_UP.getBsmDetailCode()
                );
        if (optional.isEmpty()) {

            return "";
        }

        return optional.get().getCode();
    }

}
