package ricciliao.bsm.utils;

import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.po.BsmUserPo;

public class BsmPojoUtils {
    private BsmPojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static BsmUserPo convert2Po(BsmUserInfoDto dto) {
        BsmUserPo po = new BsmUserPo();
        po.setId(dto.getId());
        po.setLoginName(dto.getLoginName());
        po.setUserName(dto.getUserName());
        po.setUserPassword(dto.getUserPassword());
        po.setUserEmail(dto.getUserEmail());
        po.setLastLoginDtm(dto.getLastLoginDtm());
        po.setStatusId(dto.getStatusId());
        po.setCreatedBy(dto.getCreatedBy());
        po.setCreatedDtm(dto.getCreatedDtm());
        po.setUpdatedBy(dto.getUpdatedBy());
        po.setUpdatedDtm(dto.getUpdatedDtm());
        po.setVersion(dto.getVersion());

        return po;
    }

}
