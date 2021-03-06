/*******************************************************************************
 * Copyright 2012 Antonio Cuomo
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package it.unisannio.ing.perflab.jades.scheduler;

import java.io.Serializable;

import org.apache.commons.javaflow.Continuation;

import it.unisannio.ing.perflab.jades.core.Process;

class ContinuationSchedulerRunner implements Runnable, Serializable,
        Runner {

    private Scheduler scheduler;
    private Continuation processContinuation;

    public ContinuationSchedulerRunner(Scheduler s) {
        this.scheduler = s;
    }

    public void start() {
        Continuation c = Continuation.startWith(this);
        this.processContinuation = c;
    }

    public void run() {
        scheduler.run();
    }

    public void block() {
        Continuation.suspend();

    }

    public void terminate() {
        Continuation.exit();

    }
    public void resume() {
        Continuation d = Continuation.continueWith(this.processContinuation);
        if (d != null)
            this.processContinuation = d;

    }
}
