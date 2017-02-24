// Copyright (C) 2015 Ericsson
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.ericsson.gerrit.plugins.highavailability;

import com.google.inject.Inject;

import com.ericsson.gerrit.plugins.highavailability.HttpResponseHandler.HttpResult;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

class HttpSession {
  private final CloseableHttpClient httpClient;
  private final String url;

  @Inject
  HttpSession(CloseableHttpClient httpClient,
      @SyncUrl String url) {
    this.httpClient = httpClient;
    this.url = url;
  }

  HttpResult post(String endpoint) throws IOException {
    return httpClient.execute(new HttpPost(url + endpoint),
        new HttpResponseHandler());
  }

  HttpResult delete(String endpoint) throws IOException {
    return httpClient.execute(new HttpDelete(url + endpoint),
        new HttpResponseHandler());
  }
}
