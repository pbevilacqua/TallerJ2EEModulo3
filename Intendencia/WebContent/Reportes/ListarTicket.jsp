<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      >
      
         <h:head>
 
   </h:head>
    <h:body>
        <h:dataTable value="#{resultadoBean.listaUsuarios}" var="usuario" border="1">
        <h:column>
            <f:facet name="header">
               <h:outputText value="ID"></h:outputText>
            </f:facet>
            <h:outputText value="#{usuario.id}"></h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
               <h:outputText value="Nombre"></h:outputText>
            </f:facet>
            <h:outputText value="#{usuario.nombre}"></h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
               <h:outputText value="Rut"></h:outputText>
            </f:facet>
            <h:outputText value="#{usuario.rut}"></h:outputText>
        </h:column>
        <h:column>
            <f:facet name="header">
               <h:outputText value="Edad"></h:outputText>
            </f:facet>
            <h:outputText value="#{usuario.edad}"></h:outputText>
        </h:column>
 
        </h:dataTable>
 
    </h:body>
 
</html>
