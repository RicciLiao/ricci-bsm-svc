package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmUserAvatarLogId;
import ricciliao.bsm.pojo.po.BsmUserAvatarLogPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmUserAvatarLogRepository extends JpaRepository<BsmUserAvatarLogPo, BsmUserAvatarLogId> {

    @Override
    void flush();

    @Override
    <S extends BsmUserAvatarLogPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmUserAvatarLogPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmUserAvatarLogPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<BsmUserAvatarLogId> bsmUserAvatarLogIds);

    @Override
    void deleteAllInBatch();

    @Override
    BsmUserAvatarLogPo getReferenceById(BsmUserAvatarLogId bsmUserAvatarLogId);

    @Override
    <S extends BsmUserAvatarLogPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmUserAvatarLogPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends BsmUserAvatarLogPo> List<S> saveAll(Iterable<S> entities);

    @Override
    List<BsmUserAvatarLogPo> findAll();

    @Override
    List<BsmUserAvatarLogPo> findAllById(Iterable<BsmUserAvatarLogId> bsmUserAvatarLogIds);

    @Override
    <S extends BsmUserAvatarLogPo> S save(S entity);

    @Override
    Optional<BsmUserAvatarLogPo> findById(BsmUserAvatarLogId bsmUserAvatarLogId);

    @Override
    boolean existsById(BsmUserAvatarLogId bsmUserAvatarLogId);

    @Override
    long count();

    @Override
    void deleteById(BsmUserAvatarLogId bsmUserAvatarLogId);

    @Override
    void delete(BsmUserAvatarLogPo entity);

    @Override
    void deleteAllById(Iterable<? extends BsmUserAvatarLogId> bsmUserAvatarLogIds);

    @Override
    void deleteAll(Iterable<? extends BsmUserAvatarLogPo> entities);

    @Override
    void deleteAll();

    @Override
    List<BsmUserAvatarLogPo> findAll(Sort sort);

    @Override
    Page<BsmUserAvatarLogPo> findAll(Pageable pageable);

    @Override
    <S extends BsmUserAvatarLogPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmUserAvatarLogPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmUserAvatarLogPo> long count(Example<S> example);

    @Override
    <S extends BsmUserAvatarLogPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmUserAvatarLogPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
