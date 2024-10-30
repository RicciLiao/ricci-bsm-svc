package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmUserInfoPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmUserRepository extends JpaRepository<BsmUserInfoPo, Long> {

    @Override
    List<BsmUserInfoPo> findAll();

    @Override
    List<BsmUserInfoPo> findAll(Sort sort);

    @Override
    List<BsmUserInfoPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends BsmUserInfoPo> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends BsmUserInfoPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmUserInfoPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmUserInfoPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    BsmUserInfoPo getReferenceById(Long aLong);

    @Override
    <S extends BsmUserInfoPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmUserInfoPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<BsmUserInfoPo> findAll(Pageable pageable);

    @Override
    <S extends BsmUserInfoPo> S save(S entity);

    @Override
    Optional<BsmUserInfoPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(BsmUserInfoPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends BsmUserInfoPo> entities);

    @Override
    void deleteAll();

    @Override
    <S extends BsmUserInfoPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmUserInfoPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmUserInfoPo> long count(Example<S> example);

    @Override
    <S extends BsmUserInfoPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmUserInfoPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    boolean existsByLoginNameOrUserEmail(String loginName, String userEmail);

    boolean existsByUserEmail(String userEmail);
}
