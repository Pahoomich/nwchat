package com.nwchat.repository;

import com.nwchat.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocsRepository extends JpaRepository<Doc, Long> {
}
