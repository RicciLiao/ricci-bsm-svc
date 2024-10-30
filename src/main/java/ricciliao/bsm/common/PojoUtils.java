package ricciliao.bsm.common;

import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.po.BsmUserInfoPo;

public class PojoUtils {
    private PojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static BsmUserInfoPo convert2Po(BsmUserInfoDto dto) {
        BsmUserInfoPo po = new BsmUserInfoPo();
        po.setId(dto.getId());
        po.setLoginName(dto.getLoginName());
        po.setUserName(dto.getUserName());
        po.setUserPassword(dto.getUserPassword());
        po.setUserEmail(dto.getUserEmail());
        po.setLastLoginDtm(dto.getLastLoginDtm());
        po.setStatus(dto.getStatus());
        po.setCreatedBy(dto.getCreatedBy());
        po.setCreatedDtm(dto.getCreatedDtm());
        po.setUpdatedBy(dto.getUpdatedBy());
        po.setUpdatedDtm(dto.getUpdatedDtm());
        po.setVersion(dto.getVersion());

        return po;
    }

}
