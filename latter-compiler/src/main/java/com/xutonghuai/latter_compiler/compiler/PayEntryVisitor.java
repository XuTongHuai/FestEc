package com.xutonghuai.latter_compiler.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * Created by xutonghuai on 2018-03-24.
 */

public final class PayEntryVisitor extends SimpleAnnotationValueVisitor7<Void,Void> {

    //需要遍历的东西
    private Filer mFiler = null ;
    private TypeMirror typeMirror = null;
    private String mPackageName = null;

    public void setFILER(Filer filer) {
        mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        mPackageName = s;
        return p;
   }

    @Override
    public Void visitType(TypeMirror t, Void p) {
        typeMirror = t;
        generateJavaCode();
        return p;
    }

    private void generateJavaCode(){
        final TypeSpec targetActivity =
                TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                        //集成什么类，来自模板中的类类型
                .superclass(TypeName.get(typeMirror))
                .build();

        //生成java文件，设置类所处的包命
        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi",targetActivity)
                .addFileComment("微信充值入口文件")
                .build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
