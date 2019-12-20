package com.nwchat.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstTestRepo<T> implements CrudRepository<T, Integer> {
	private List<T> list;


	public AbstTestRepo() {
		this.list = new ArrayList<>();
	}

	@Override
	public <S extends T> S save(S s) {

//		s.setId(list.size() + 1);

		list.add(s);

		return s;
	}

	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
		for (S s : iterable) {
			list.add(s);
		}
		return iterable;
	}

	@Override
	public Optional<T> findById(Integer integer) {

		if (integer > -1 && integer < list.size()) {
			return Optional.of(list.get(integer));
		}
		return Optional.empty();
	}

	@Override
	public boolean existsById(Integer integer) {
		return false;
	}

	@Override
	public Iterable<T> findAll() {
		return list;
	}

	@Override
	public Iterable<T> findAllById(Iterable<Integer> iterable) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void deleteById(Integer integer) {

	}

	@Override
	public void delete(T t) {

	}

	@Override
	public void deleteAll(Iterable<? extends T> iterable) {

	}

	@Override
	public void deleteAll() {

	}
}
