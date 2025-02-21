tools.dynamia.zk.ui.signaturepad.SignaturePad = zk.$extends(zk.Widget, {

    _value: '',
    _penColor: '',
    _maxWidth: '',
    _minWidth: '',

    getValue: function () {
        return this._value;
    },


    setValue: function (value) {
        if (this._value !== value) {
            this._value = value;
            if (this.pad) {
                if (this._value == null || this._value === '') {
                    this.pad.clear();
                } else {
                    this.pad.fromDataURL(this._value);
                }
            }
        }
    },

    getPenColor: function () {
        return this._penColor;
    },


    setPenColor: function (penColor) {
        if (this._penColor !== penColor) {
            this._penColor = penColor;
            if (this.pad) {
                this.pad.penColor = penColor;
            }
        }
    },

    getMinWidth: function () {
        return this._minWidth;
    },


    setMinWidth: function (minWidth) {
        if (this._minWidth !== minWidth) {
            if (this.pad) {
                this.pad.minWidth = minWidth;
            } else {
                this._minWidth = minWidth;
            }
        }
    },

    getMaxWidth: function () {
        return this._maxWidth;
    },


    setMaxWidth: function (maxWidth) {
        if (this._maxWidth !== maxWidth) {
            this._maxWidth = maxWidth;
            if (this.pad) {
                this.pad.maxWidth = maxWidth;
            }
        }
    },


    bind_: function () {
        this.$supers(tools.dynamia.zk.ui.signaturepad.SignaturePad, 'bind_', arguments);

        var ctx = document.getElementById(this.uuid);
        this.pad = new SignaturePad(ctx);

        if (this._penColor) {
            this.pad.penColor = this._penColor;
        }

        if (this._minWidth) {
            this.pad.minWidth = this._minWidth;
        }

        if (this._maxWidth) {
            this.pad.maxWidth = this._maxWidth;
        }

        if (this._value) {
            this.pad.fromDataURL(this._value);
        }

        //Send events to server
        this.pad.addEventListener("endStroke", () => {
            this._value = this.pad.toDataURL();
            this.smartUpdate("value", this._value, true)
            this.fire("onChange", {value: this._value}, {toServer: true});
        }, {once: false});
    },


    unbind_: function () {
        this.$supers(tools.dynamia.zk.ui.signaturepad.SignaturePad, 'unbind_', arguments);
    },

    redraw: function (out) {
        out.push("<canvas", this.domAttrs_(), "></canvas>");
    }
});
