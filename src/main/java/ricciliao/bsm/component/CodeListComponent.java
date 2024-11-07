package ricciliao.bsm.component;

import ricciliao.bsm.common.BsmCode;
import ricciliao.bsm.pojo.dto.BsmCodeDetailDto;
import ricciliao.bsm.repository.BsmCodeDetailRepository;
import ricciliao.bsm.repository.BsmCodeRepository;

import java.util.Optional;

public class CodeListComponent {

    private final BsmCodeDetailRepository bsmCodeDetailRepository;
    private final BsmCodeRepository bsmCodeRepository;

    public CodeListComponent(BsmCodeDetailRepository bsmCodeDetailRepository,
                             BsmCodeRepository bsmCodeRepository) {
        this.bsmCodeDetailRepository = bsmCodeDetailRepository;
        this.bsmCodeRepository = bsmCodeRepository;
    }

    public String getVerificationForSignUp() {
        Optional<BsmCodeDetailDto> optional =
                bsmCodeDetailRepository.getByUniqueKey(BsmCode.EMAIL_VERIFICATION, BsmCode.BsmCodeEnum.EMAIL_VERIFICATION_FOR_SIGN_UP);
        if (optional.isEmpty()) {

            return "";
        }

        return optional.get().getCode();
    }

}
