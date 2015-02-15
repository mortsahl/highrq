package com.highrq.api.controllers;

import com.highrq.api.resources.AccountListResource;
import com.highrq.api.resources.AccountResource;
import com.highrq.api.resources.BlogListResource;
import com.highrq.api.resources.BlogResource;
import org.springframework.http.ResponseEntity;


public interface AccountController {

    public ResponseEntity<AccountListResource> findAllAccounts(String username);
    public ResponseEntity<AccountResource> createAccount(AccountResource sentAccount);
    public ResponseEntity<AccountResource> getAccount(Long accountId);
    public ResponseEntity<BlogResource> createBlog(Long accountId, BlogResource res);
    public ResponseEntity<BlogListResource> findAllBlogs(Long accountId);
    public ResponseEntity<AccountListResource> findAccountsByRole(String role);

}

