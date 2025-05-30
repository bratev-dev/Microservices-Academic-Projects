package com.unicauca.CoordinatorService.domain.model;

import com.unicauca.CoordinatorService.domain.state.*;
import com.unicauca.CoordinatorService.domain.state.ProjectState;
/**
 *
 * @author jpala
 */
public enum ProjectStatus {
    RECEIVED {
        @Override
        public ProjectState getState() {
            return new Received();
        }
    },
    ACCEPTED {
        @Override
        public ProjectState getState() {
            return new Accepted();
        }
    },
    REJECTED {
        @Override
        public ProjectState getState() {
            return new Rejected();
        }
    },
    IN_PROGRESS {
        @Override
        public ProjectState getState() {
            return new InProgress();
        }
    },
    CLOSED {
        @Override
        public ProjectState getState() {
            return new Closed();
        }
    };

    public abstract ProjectState getState();
}
