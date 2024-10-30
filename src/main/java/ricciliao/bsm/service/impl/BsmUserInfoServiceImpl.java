package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.common.PojoUtils;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.exception.ParameterException;
import ricciliao.common.component.exception.RecordException;

import java.time.LocalDateTime;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;

    @Autowired
    public void setBsmUserRepository(BsmUserRepository bsmUserRepository) {
        this.bsmUserRepository = bsmUserRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long signUp(BsmUserInfoDto requestDto) throws CmnException {
        requestDto.setVersion(null);
        requestDto.setId(null);
        requestDto.setLastLoginDtm(null);
        requestDto.setCreatedDtm(LocalDateTime.now());
        requestDto.setUpdatedDtm(LocalDateTime.now());
        if (bsmUserRepository.existsByLoginNameOrUserEmail(requestDto.getLoginName(), requestDto.getLoginName())) {

            throw new RecordException();
        }

        requestDto.setStatus("A");
        requestDto.setUserPassword("A");
        bsmUserRepository.save(PojoUtils.convert2Po(requestDto));

        return 0L;
    }

    @Override
    public Long signUpSendPost(UserSignUpSendPostDto requestDto) throws CmnException {
        if(bsmUserRepository.existsByUserEmail(requestDto.getEmailAddress())) {

            throw new ParameterException();
        }

        return 0L;
    }

}
