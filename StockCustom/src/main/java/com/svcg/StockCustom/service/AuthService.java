package com.svcg.StockCustom.service;

import java.io.IOException;

public interface AuthService {

    void resetPasswordByEmail(String email) throws IOException;
}
