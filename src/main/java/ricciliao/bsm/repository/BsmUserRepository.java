package ricciliao.bsm.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import ricciliao.bsm.pojo.po.BsmUserPo;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BsmUserRepository extends JpaRepository<BsmUserPo, Long> {

    @Override
    List<BsmUserPo> findAll();

    @Override
    List<BsmUserPo> findAll(Sort sort);

    @Override
    List<BsmUserPo> findAllById(Iterable<Long> longs);

    @Override
    <S extends BsmUserPo> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends BsmUserPo> S saveAndFlush(S entity);

    @Override
    <S extends BsmUserPo> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<BsmUserPo> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    BsmUserPo getReferenceById(Long aLong);

    @Override
    <S extends BsmUserPo> List<S> findAll(Example<S> example);

    @Override
    <S extends BsmUserPo> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<BsmUserPo> findAll(Pageable pageable);

    @Override
    <S extends BsmUserPo> S save(S entity);

    @Override
    Optional<BsmUserPo> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(BsmUserPo entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends BsmUserPo> entities);

    @Override
    void deleteAll();

    @Override
    <S extends BsmUserPo> Optional<S> findOne(Example<S> example);

    @Override
    <S extends BsmUserPo> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends BsmUserPo> long count(Example<S> example);

    @Override
    <S extends BsmUserPo> boolean exists(Example<S> example);

    @Override
    <S extends BsmUserPo, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    boolean existsByLoginNameOrUserEmail(String loginName, String userEmail);

    Optional<BsmUserPo> findByLoginName(String loginName);
}
