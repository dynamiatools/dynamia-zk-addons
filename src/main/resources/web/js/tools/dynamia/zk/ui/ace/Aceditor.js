tools.dynamia.zk.ui.ace.Aceditor = zk.$extends(zul.inp.Textbox, {

    _editor: null,

    $define: {
        theme: null,
        mode: null,
        showgutter: null,
        fontSize: null
    },

    setValue: function (value, fromServer) {
        this.$supers('setValue', arguments);
        if (fromServer && this._editor) {
            this._editor.setValue(value);
        }
    },

    setHeight: function (value) {
        this.$supers('setHeight', arguments);
        if (this._editor) {
            this._editor.resize();
        }
    },

    setWidth: function (value) {
        this.$supers('setWidth', arguments);
        if (this._editor) {
            this._editor.resize();
        }
    },

    setHflex: function (value) {
        this.$supers('setHflex', arguments);
        if (this._editor) {
            this._editor.resize();
        }
    },

    setVflex: function (value) {
        this.$supers('setVflex', arguments);
        if (this._editor) {
            this._editor.resize();
        }
    },


    bind_: function () {
        this.$supers('bind_', arguments);


        var elem = this.$n(), widget = this, editor = ace.edit(this.uuid), session, base, lines, min, max;
        lines = editor.getSession().getDocument().getLength();
        base = zk.ajaxURI('/web/js/tools/dynamia/zk/ui/ace/', {au: true});
        ace.config.set('basePath', base);
        session = editor.getSession();
        this._editor = editor;


        if (this._theme) {
            editor.setTheme('ace/theme/' + this._theme);
        }
        if (this._mode) {
            session.setMode('ace/mode/' + this._mode);
        }
        if (!this._fontSize) {
            this._fontSize = 14
        }


        editor.renderer.setShowGutter(this._showgutter);
        editor.setReadOnly(this._readonly);
        editor.setAutoScrollEditorIntoView(true);
        editor.setOptions({fontSize: this._fontSize});

        editor.on('blur', function (e) {
            widget.setValue(editor.getValue());
            widget.fireOnChange();
        });
        session.on("changeAnnotation", function () {
            var annot = session.getAnnotations();
            widget.smartUpdate('annotations', annot);
        });

        setTimeout(function () {
            editor.resize();
        }, 500);
        editor.resize();
    },

    unbind_: function () {
        if (this._editor) {
            this._editor.destroy();
            this._editor = null;
        }
        this.$supers('unbind_', arguments);
    },

    redraw: function (out) {

        out.push('<div', this.domAttrs_(), '>');
        if (this.getValue()) {
            out.push(zUtl.encodeXML(this._value, {multiline: true}));
        }
        out.push('</div>');
    }

});