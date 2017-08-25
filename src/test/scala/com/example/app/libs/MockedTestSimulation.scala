package com.example.app.libs

trait LocalTestSimulation
  extends TestSimulation
  with TestApplication {
  before {
    start()
  }

  after {
    stop()
  }
}

trait MockedTestSimulation
  extends TestSimulation
  with LocalTestSimulation {
  before {
    TestStubServer.start()
  }

  after {
    TestStubServer.stop()
  }
}