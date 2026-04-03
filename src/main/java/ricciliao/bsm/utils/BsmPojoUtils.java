package ricciliao.bsm.utils;

import ricciliao.bsm.pojo.dto.BsmUserDto;
import ricciliao.bsm.pojo.po.BsmUserLogPo;
import ricciliao.bsm.pojo.po.BsmUserPo;
import ricciliao.x.component.persistence.LogAction;

public class BsmPojoUtils {
    private BsmPojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static BsmUserPo convert2Po(BsmUserDto dto) {
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

    public static BsmUserLogPo convert2Po(BsmUserPo po, LogAction.Op<BsmUserLogPo> op) {
        BsmUserLogPo logPo = new BsmUserLogPo();
        logPo.setId(po.getId());
        logPo.setLoginName(po.getLoginName());
        logPo.setUserName(po.getUserName());
        logPo.setUserPassword(po.getUserPassword());
        logPo.setUserEmail(po.getUserEmail());
        logPo.setLastLoginDtm(po.getLastLoginDtm());
        logPo.setStatusId(po.getStatusId());
        logPo.setCreatedBy(po.getCreatedBy());
        logPo.setCreatedDtm(po.getCreatedDtm());
        logPo.setUpdatedBy(po.getUpdatedBy());
        logPo.setUpdatedDtm(po.getUpdatedDtm());
        logPo.setVersion(po.getVersion());
        logPo.setActionBy(po.getUpdatedBy());

        return op.apply(logPo);
    }

}
