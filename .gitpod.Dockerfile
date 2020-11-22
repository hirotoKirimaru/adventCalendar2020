FROM gitpod/workspace-postgres

USER gitpod

RUN bash -c ". /home/gitpod/.sdkman/bin/sdkman-init.sh \
             && sdk install java 15.0.1.fx-zulu"