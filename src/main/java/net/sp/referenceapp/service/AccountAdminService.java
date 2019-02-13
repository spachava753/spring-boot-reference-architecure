package net.sp.referenceapp.service;

import java.util.List;
import org.springframework.dao.DataAccessException;
import net.sp.referenceapp.dto.AccountDTO;

public interface AccountAdminService {

    List<AccountDTO> findAllAccounts() throws DataAccessException;

}