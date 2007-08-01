/*
 * Copyright 2000-2007 JetBrains s.r.o.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.plugins.groovy.lang.psi.impl.statements.branch;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.groovy.lang.parser.GroovyElementTypes;
import org.jetbrains.plugins.groovy.lang.psi.GroovyElementVisitor;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrLabeledStatement;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.GrLoopStatement;
import org.jetbrains.plugins.groovy.lang.psi.api.statements.branch.GrBreakStatement;
import org.jetbrains.plugins.groovy.lang.psi.impl.GroovyPsiElementImpl;

/**
 * @author ilyas
 */
public class GrBreakStatementImpl extends GroovyPsiElementImpl implements GrBreakStatement {
  public GrBreakStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(GroovyElementVisitor visitor) {
    visitor.visitBreakStatement(this);
  }

  public String toString() {
    return "BREAK statement";
  }

  @Nullable
  public String getLabel() {
    final PsiElement id = findChildByType(GroovyElementTypes.mIDENT);
    if (id == null) return null;
    return id.getText();
  }

  @Nullable
  public GrLoopStatement getBreakedLoop() {
    final String label = getLabel();
    GrLoopStatement loop = PsiTreeUtil.getParentOfType(this, GrLoopStatement.class);
    if (label == null) return loop;
    while (loop != null) {
      if (loop.getParent() instanceof GrLabeledStatement && label.equals(((GrLabeledStatement) loop.getParent()).getLabel())) {
        return loop;
      }
      loop = PsiTreeUtil.getParentOfType(loop, GrLoopStatement.class);
    }

    return null;
  }
}
