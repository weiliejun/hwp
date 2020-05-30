(function () {
    $(".ds-post-button").live("click", function () {
        var pl_str = $(this).parent().parent().find("textarea").val();
        if (pl_str.length > 500) {//限制评论字数，超过500个字的评论大多是垃圾评论
            alert("您的评论内容太长，请删除部分之后再提交！");
            return false;
        }
        if (pl_str.length < 5) {//限制评论字数，少于5个字的评论大多是垃圾评论
            alert("您的评论内容太短，请不要发表无意义的评论哦！");
            return false;
        }
        //垃圾评论过滤，其他过滤规则可以自行增加正则表达式来实现
        if (/\d{7,}/i.test(pl_str) || //连续7个以上数字，过滤发Q号和Q群的评论
            /(\d.*){7,}/i.test(pl_str) || //非连续的7个以上数字，过滤用字符间隔开的Q号和Q群的评论
            /\u52A0.*\u7FA4/i.test(pl_str) || //包含“加群”两字的通常是发Q群的垃圾评论
            /(\u9876.*){5,}/i.test(pl_str) || //过滤5个以上“顶”字的评论
            /([\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u25CB\u58F9\u8D30\u53C1\u8086\u4F0D\u9646\u67D2\u634C\u7396\u96F6].*){7,}/i.test(pl_str) //过滤用汉字发Q号和Q群的评论
        ) {
            alert("请不要发表灌水、广告、违法、Q群Q号等信息，感谢您的配合！");
            //$("form textarea").val("");//这里的操作可以根据自己的情况修改
            return false;
        }
    });
});

///
(function (a, b) {
    function r(a) {
        var b = 0;
        if (c.selection) {
            a.focus();
            var d = c.selection.createRange();
            d.moveStart("character", -a.value.length),
                b = d.text.length
        } else if (a.selectionStart || a.selectionStart == "0") b = a.selectionStart;
        return b
    }

    function s(a, b) {
        if (a.setSelectionRange) a.focus(),
            a.setSelectionRange(b, b);
        else if (a.createTextRange) {
            var c = a.createTextRange();
            c.collapse(!0),
                c.moveEnd("character", b),
                c.moveStart("character", b),
                c.select()
        }
    }

    var c = a.document,
        d = c.getElementsByTagName("head")[0] || c.getElementsByTagName("body")[0],
        e = c.createElement("input"),
        f = a.encodeURI,
        g = a.navigator.userAgent,
        h = function () {
            var a = {
                    "<": "&lt;",
                    ">": "&gt;",
                    '"': "&quot;",
                    "'": "&#x27;",
                    "`": "&#x60;"
                },
                b = /&(?!\w+;)|[<>"'`]/g,
                c = /[&<>"'`]/,
                d = function (b) {
                    return a[b] || "&amp;"
                };
            return function (a) {
                return a == null || a === !1 ? "" : c.test(a) ? a.replace(b, d) : a
            }
        }(),
        i = {
            APPPATH: '/m3',
            SERVER: '/community/comment',
            STATIC: '/resource/ui/core/component/comment',
            loaded: {
                easyXDM: typeof easyXDM == "object",
                JSON: typeof a.JSON == "object" && a.JSON,
                jQuery: typeof jQuery != "undefined" && jQuery.fn.jquery >= "1.4.3"
            },
            libs: {
                easyXDM: "/js/easyXDM.min.js",
                JSON: "/js/json2.min.js",
                jQuery: "/js/embed.compat.js"
            },
            selectors: {
                "#itech-thread": !0
            },
            support: {
                autofocus: "autofocus" in e,
                placeholder: "placeholder" in e,
                multiple: "multiple" in e,
                iOS: g.match(/ \((iPad|iPhone|iPod);( U;)? CPU( iPhone)? OS /),
                android: g.match(/ \(Linux; U; Android /)
            },
            timeOffset: 0,
            checkResponse: function (a, b) {
                return function (c) {
                    try {
                        var d = easyXDM.getJSONObject().parse(c.data);
                        switch (d.code) {
                            case 0:
                                a(d.response, d.cursor);
                                break;
                            default:
                                b && b(d.response),
                                    alert("Error " + d.code + ":" + d.errorMessage),
                                d.errorTrace && alert(d.errorTrace)
                        }
                    } catch (e) {
                    }
                }
            },
            parseDate: function (a) {
                return a.parse("2011-10-28T00:00:00+08:00") &&
                    function (b) {
                        return new a(b)
                    } || a.parse("2011/10/28T00:00:00+0800") &&
                    function (b) {
                        return new a(b.replace(/-/g, "/").replace(/:(\d\d)$/, "$1"))
                    } || a.parse("2011/10/28 00:00:00+0800") &&
                    function (b) {
                        return new a(b.replace(/-/g, "/").replace(/:(\d\d)$/, "$1").replace("T", " "))
                    } ||
                    function (b) {
                        return new a(b)
                    }
            }(Date),
            fullTime: function (a) {
                var b = i.parseDate(a);
                return b.getFullYear() + "年" + (b.getMonth() + 1) + "月" + b.getDate() + "日 " + b.toLocaleTimeString()
            },
            elapsedTime: function (a) {
                var b = i.parseDate(a),
                    c = new Date,
                    d = (c - i.timeOffset - b) / 1e3;
                return d < 10 ? "刚刚" : d < 60 ? Math.round(d) + "秒前" : d < 3600 ? Math.round(d / 60) + "分钟前" : d < 86400 ? Math.round(d / 3600) + "小时前" : (c.getFullYear() == b.getFullYear() ? "" : b.getFullYear() + "年") + (b.getMonth() + 1) + "月" + b.getDate() + "日"
            },
            injectScript: function (b, e) {
                var f = c.createElement("script");
                f.type = "text/javascript",
                    f.src = b,
                e && (f.addEventListener ? f.addEventListener("load", e, !1) : f.attachEvent && f.attachEvent("onreadystatechange", function (b) {
                    var c = b.srcElement.readyState;
                    (c == "loaded" || c == "complete") && e.call(a)
                })),
                    d.appendChild(f)
            },
            require: function (a, b) {
                if (typeof a == "string") i.loaded[a] ? b() : i.injectScript(i.APPPATH + i.STATIC + i.libs[a], function () {
                    i.loaded[a] = !0,
                        b()
                });
                else if (typeof a == "object") {
                    var c, d = !0;
                    for (c = 0; c < a.length; c++) (function (e) {
                        if (i.loaded[a[c]]) return;
                        d = !1,
                            i.injectScript(i.APPPATH + i.STATIC + i.libs[e], function () {
                                i.loaded[e] = !0;
                                for (var c = 0; c < a.length; c++) if (!i.loaded[a[c]]) return;
                                b()
                            })
                    })(a[c]);
                    d && b()
                }

            },
            injectStylesheet: function (a) {
                var b = c.createElement("link");
                b.type = "text/css",
                    b.rel = "stylesheet",
                    b.href = a,
                    d.appendChild(b)

            },
            injectStyle: function (a) {
                var b = c.createElement("style");
                b.type = "text/css",
                    b.innerHTML = a,
                    d.appendChild(b)

            },
            init: function (threadId) {
                threadId && (i.selectors[threadId] = !0);
                jQuery && (jQuery.isReady || !jQuery.browser.msie) && jQuery.each(i.selectors, function (threadId, c) {
                    var el = jQuery(threadId);
                    if (el.length == 0 || el.data("initialized")) return;
                    el.data("initialized", !0),
                        i.initEmbedThread(el)
                })
            }
        },
        j = i.pagelets = {},
        k = i.jsonData = {},
        l = i.Collections = {},
        m = i.Views = {},
        n = i.Callbacks = {},
        o = function (a) {
            return function () {
                return a
            }
        },
        p = i.smilies = {
            "默认表情": {
                "[呵呵]": "smilea_thumb.gif",
                "[嘻嘻]": "tootha_thumb.gif",
                "[哈哈]": "laugh.gif",
                "[可爱]": "tza_thumb.gif",
                "[可怜]": "kl_thumb.gif",
                "[挖鼻屎]": "kbsa_thumb.gif",
                "[吃惊]": "cj_thumb.gif",
                "[害羞]": "shamea_thumb.gif",
                "[挤眼]": "zy_thumb.gif",
                "[闭嘴]": "bz_thumb.gif",
                "[鄙视]": "bs2_thumb.gif",
                "[爱你]": "lovea_thumb.gif",
                "[泪]": "sada_thumb.gif",
                "[偷笑]": "heia_thumb.gif",
                "[亲亲]": "qq_thumb.gif",
                "[生病]": "sb_thumb.gif",
                "[太开心]": "mb_thumb.gif",
                "[懒得理你]": "ldln_thumb.gif",
                "[右哼哼]": "yhh_thumb.gif",
                "[左哼哼]": "zhh_thumb.gif",
                "[嘘]": "x_thumb.gif",
                "[衰]": "cry.gif",
                "[委屈]": "wq_thumb.gif",
                "[吐]": "t_thumb.gif",
                "[打哈气]": "k_thumb.gif",
                "[抱抱]": "bba_thumb.gif",
                "[怒]": "angrya_thumb.gif",
                "[疑问]": "yw_thumb.gif",
                "[馋嘴]": "cza_thumb.gif",
                "[拜拜]": "88_thumb.gif",
                "[思考]": "sk_thumb.gif",
                "[汗]": "sweata_thumb.gif",
                "[困]": "sleepya_thumb.gif",
                "[睡觉]": "sleepa_thumb.gif",
                "[钱]": "money_thumb.gif",
                "[失望]": "sw_thumb.gif",
                "[酷]": "cool_thumb.gif",
                "[花心]": "hsa_thumb.gif",
                "[哼]": "hatea_thumb.gif",
                "[鼓掌]": "gza_thumb.gif",
                "[晕]": "dizzya_thumb.gif",
                "[悲伤]": "bs_thumb.gif",
                "[抓狂]": "crazya_thumb.gif",
                "[黑线]": "h_thumb.gif",
                "[阴险]": "yx_thumb.gif",
                "[怒骂]": "nm_thumb.gif",
                "[心]": "hearta_thumb.gif",
                "[伤心]": "unheart.gif",
                "[猪头]": "pig.gif",
                "[ok]": "ok_thumb.gif",
                "[耶]": "ye_thumb.gif",
                "[good]": "good_thumb.gif",
                "[不要]": "no_thumb.gif",
                "[赞]": "z2_thumb.gif",
                "[来]": "come_thumb.gif",
                "[弱]": "sad_thumb.gif",
                "[蜡烛]": "lazu_thumb.gif",
                "[钟]": "clock_thumb.gif",
                "[蛋糕]": "cake.gif",
                "[路过这儿]": "lxhluguo_thumb.gif",
                "[不要啊]": "chn_buyaoya_thumb.gif",
                "[dx泪奔]": "daxiongleibenxiong_thumb.gif",
                "[运气中]": "cat_yunqizhong_thumb.gif",
                "[有钱]": "youqian_thumb.gif",
                "[冲锋]": "cf_thumb.gif",
                "[浮云]": "fuyun_thumb.gif",
                "[给力]": "geili_thumb.gif",
                "[围观]": "wg_thumb.gif",
                "[威武]": "vw_thumb.gif",
                "[囧]": "j_thumb.gif"
            }
        },
        q = i.templates = {
            authorAnchor: function (a) {
                var b = a.author_name || a.author.name,
                    c = a.author_url || a.author.url;
                return c ? '<a rel="author" target="_blank" href="' + f(c) + '">' + h(b) + "</a>" : h(b)
            },
            post: function (a) {
                var b = '<img src="' + f(a.author && i.APPPATH + a.author.avatarUrl || i.APPPATH + i.STATIC + "/images/noavatar_default.jpg") + '" />',
                    c = '<span class="ds-time" datetime="' + a.createTime + '" title="' + i.fullTime(a.createTime) + '">' + i.elapsedTime(a.createTime) + "</span>";
                return '<div class="ds-post-self' + '" data-post-id="' + a.postId + '" data-thread-id="' + a.threadId + '">' + '<div class="ds-post-color"></div>' + '<div class="ds-post-main">' + '<div class="ds-avatar">' + (a.author_url ? '<a href="' + f(a.author_url) + '" rel="nofollow" target="_blank">' + b + "</a>" : b) + "</div>" + '<div class="ds-comment-body">' + '<div class="ds-comment-header">' + (a.author ? '<a class="ds-user-name ds-highlight" href="' + f(a.author.url || "javascript:void(0)") + '" rel="nofollow" target="_blank">' + h(a.author.name) + "</a>" : '<span class="ds-user-name">' + h(a.author_name) + "</span>") + "</div>" + "<p>" + a.content + "</p>" + '<div class="ds-comment-footer">' + (a.url ? '<a href="' + a.url + '" target="_blank" rel="nofollow">' + c + "</a>" : c) + '<a class="ds-post-reply"><span class="ds-ui-icon"></span>回复</a>' + (a.privileges["delete"] ? ' <a class="ds-post-delete"><span class="ds-ui-icon"></span> 删除</a>' : "") + "</div>" + "</div>" + "</div>" + "</div>"
            },
            replybox: function (a, b) {
                var c = k.visitor,
                    d = [];
                return '<div class="ds-replybox"><form method="post" action="" onsubmit="return false;"><input type="hidden" name="threadId" value="' + a + '" /><input type="hidden" name="type" value="' + k.currentThread.type + '" /><input type="hidden" name="parentId" value="" />' + (c.userId ? "" : '<input type="hidden" name="author_name" value="' + h(c.name) + '" /><input type="hidden" name="author_url" value="' + h(c.url) + '" />') + '<div class="ds-replybox-main">' + '<div class="ds-textarea-wrapper">' + '<textarea name="content" title="Ctrl+Enter快捷提交" placeholder="评论一下吧 ..."></textarea>' + '<div class="ds-hidden-text"></div>' + "</div>" + '<div class="ds-post-toolbar">' + '<div class="ds-post-options">' + '<a class="ds-toolbar-button ds-add-emote"></a>' + '<span class="ds-sync" style="color: #999;"></span>' + "</div>" + '<button class="ds-post-button" type="submit">发 布</button>' + "</div>" + "</div>" + "</form>" + "</div>"
            },
            postListHead: function (a) {
                return '<div class="ds-comments-info"><ul class="ds-comments-tabs"><li class="ds-tab"><a class="ds-comments-tab-duoshuo ds-current"><span class="ds-highlight">' + a.posts + "</span> 条评论</a></li>" + '<li class="ds-tab-border ' + '"></li>' + "</ul>" + "</div>";
            },
            toolbar: function (a) {
                return '<div class="ds-toolbar"><div class="ds-border-highlight"></div><div class="ds-visitor"><span class="ds-visitor-avatar"><img src="' + f(i.APPPATH + k.visitor.avatarUrl) + '"/>' + "</span>" + '<span class="ds-visitor-name">' + h(k.visitor.name) + "</span>" + "</div>" + "</div>"
            },
            indicator: o('<div id="ds-indicator"><img src="' + i.APPPATH + i.STATIC + '/images/loading.gif"/></div>')
        };
    i.loaded.jQuery && (i.jQuery = a.jQuery),
        i.require("jQuery", function () {
            var d = i.jQuery;
            d.extend(q, {
                postWithChildren: function (a) {
                    var b = '<li class="ds-post" data-post-id="' + a.postId + '">' + q.post(a);
                    return a.children && (b += '<div class="ds-children-wrapper"><div class="ds-children-control"><a class="ds-collapse"><span class="ds-ui-icon"></span></a><a class="ds-expand"></a></div><div class="ds-collapse-padding"></div><ul class="ds-children">' + d.map(a.children, q.postWithChildren).join("") + "</ul>" + "</div>"),
                    b + "</li>"
                },
                smiliesTooltip: function () {
                    var a = '<div class="ds-smilies-tooltip"><ul class="ds-smilies-tabs">';
                    return d.each(p, function (b, c) {
                        a += "<li><a>" + b + "</a></li>"
                    }),
                    a + "</ul>" + '<div class="ds-smilies-container"></div>' + "</div>"
                }
            }),
                m.Replybox = function (a) {
                    this.embedThread = a
                },
                m.Replybox.prototype = {
                    render: function (b) {
                        function v(a) {
                            a.data("submitting", !0),
                                a.find(".ds-post-button").addClass("ds-waiting").html("正在发布")[0].disabled = !0
                        }

                        function w(a) {
                            a.data("submitting", !1),
                                a.find(".ds-post-button").removeClass("ds-waiting").html("发 布")[0].disabled = !1
                        }

                        var e = this,
                            f = e.embedThread,
                            g = !1,
                            h = function (a) {
                                // g || (i.injectScript(i.STATIC + "/js/smilies.js"), g = !0)
                            },
                            j = e.el = d(q.replybox(f.threadId, b)).click(h),
                            l = j.find("form"),
                            n = l.find("input[type=checkbox]")[0],
                            o = l.find("span.ds-connected-sites a"),
                            p = j.find("div.ds-hidden-text"),
                            r = l.find("textarea").focus(h).keydown(function (a) {
                                if (a.ctrlKey && a.which == 13 || a.which == 10) l.data("submitting") || l.trigger("submit")
                            }).keyup(function (a) {
                                p.html(this.value.replace(/\n/g, "<br/>")),
                                    d(this).height(Math.max(54, p.height() + 27))
                            }),
                            s = j.find(".ds-add-emote").click(function (a) {
                                var b = i.smiliesTooltip;
                                if (s.toggleClass("ds-expanded").hasClass("ds-expanded")) {
                                    b || (b = i.smiliesTooltip = new m.SmiliesTooltip(e), b.render().reset("默认表情"));
                                    var g = e.el.offset(),
                                        j = f.el.offset();
                                    b.el.appendTo(f.el).css({
                                        top: g.top - j.top + e.el.outerHeight() + 5 + "px",
                                        left: g.left - j.left + "px"
                                    }).show(),
                                        d(c.body).click(t),
                                        h(a)
                                } else t(a);
                                return !1
                            }),
                            t = e.hideSmilies = function (a) {
                                s.removeClass("ds-expanded"),
                                    i.smiliesTooltip.el.hide(),
                                    d(c.body).unbind("click", t)
                            };
                        if (!i.support.placeholder) {
                            var u = r.attr("placeholder");
                            r.val(u).focus(function () {
                                this.value === u && (this.value = "")
                            }).blur(function () {
                                this.value === "" && (this.value = u)
                            })
                        }
                        var x = function () {
                            v(l),
                                /*
                                i.xhr.request({
                                    url: i.APPPATH + i.SERVER + "/post/add",
                                    method: "POST",
                                    data: i.toJSON(d(l))
                                }, i.checkResponse(function (b) {
                                    var c = f.options.order == "asc",
                                        g = f.options.formPosition == "top",
                                        h = d(q.postWithChildren(b));
                                    h = h.hide()[c ? "appendTo" : "prependTo"](e.postList);
                                    var i = h.find(".ds-post-color").show();
                                    h.slideDown(function () {
                                            i.delay(1e3).fadeOut()
                                        }),
                                    g == c && d(a).scrollTop(h.offset().top - 200);
                                    var k = f.el.find(".ds-comments-tab-duoshuo span.ds-highlight");
                                    k.html(parseInt(k.html()) + 1),
                                    r.val("").height(54),
                                    w(l),
                                    j.hasClass("ds-inline-replybox") && (j.hide(), e.replyButton.removeClass("ds-reply-active"))
                                }, function (a) {
                                    w(l)
                                }), function (a) {
                                    w(l)
                                })
                                */
                                $.ajax({
                                    url: i.APPPATH + i.SERVER + "/post/add",
                                    type: "POST",
                                    data: i.toJSON(d(l)),
                                    success: function (data) {
                                        b = data.response;
                                        var c = f.options.order == "asc",
                                            g = f.options.formPosition == "top",
                                            h = d(q.postWithChildren(b));
                                        h = h.hide()[c ? "appendTo" : "prependTo"](e.postList);
                                        var i = h.find(".ds-post-color").show();
                                        h.slideDown(function () {
                                            i.delay(1e3).fadeOut()
                                        }),
                                        g == c && d(a).scrollTop(h.offset().top - 200);
                                        var k = f.el.find(".ds-comments-tab-duoshuo span.ds-highlight");
                                        k.html(parseInt(k.html()) + 1),
                                            r.val("").height(54),
                                            w(l),
                                        j.hasClass("ds-inline-replybox") && (j.hide(), e.replyButton.removeClass("ds-reply-active"))
                                    }
                                });
                        };
                        l.submit(function () {
                            var a = d.trim(l[0].content.value);
                            return a == "" || !i.support.placeholder && a == r.attr("placeholder") ? (alert("您还没写内容呢"), !1) : (k.visitor.userId == 0 ? (alert("您没有登录，请登陆！"), !1) : x(), !1)
                        });
                        var y = function (a) {
                            var b = d(a).attr("data-service");
                            return d(a).hasClass("ds-" + b + "-active") ? b : null
                        };
                        return o.click(function () {
                            var a = d(this);
                            service = a.attr("data-service"),
                                a.toggleClass("ds-" + service + "-active"),
                                n.value = d.map(o, y).join(","),
                                n.checked = n.value != ""
                        }),
                            d(n).change(function () {
                                var a = this.checked;
                                o.each(function () {
                                    d(this)[a ? "addClass" : "removeClass"]("ds-" + d(this).attr("data-service") + "-active")
                                }),
                                    this.value = d.map(o, y).join(",")
                            }),
                            this
                    }
                },
                i.toJSON = function (a) {
                    var b = /\r?\n/g,
                        c = /^(?:color|date|datetime|datetime-local|email|hidden|month|number|password|range|search|tel|text|time|url|week)$/i,
                        e = /^(?:select|textarea)/i,
                        f = a.map(function () {
                            return this.elements ? d.makeArray(this.elements) : this
                        }).filter(function () {
                            return this.name && !this.disabled && (this.checked || e.test(this.nodeName) || c.test(this.type))
                        }).map(function (a, c) {
                            var e = d(this).val();
                            return e == null ? null : d.isArray(e) ? d.map(e, function (a, d) {
                                return {
                                    name: c.name,
                                    value: a.replace(b, "\r\n")
                                }
                            }) : {
                                name: c.name,
                                value: e.replace(b, "\r\n")
                            }
                        }).toArray(),
                        g = {};
                    return d.each(f, function () {
                        g[this.name] = this.value
                    }),
                        g
                },
                m.PostListHead = function (a, b) {
                    this.embedThread = a,
                        this.options = b
                },
                m.PostListHead.prototype = {
                    render: function () {
                        var a = this.options,
                            b = this.embedThread,
                            c = b.postList;
                        el = this.el = d(q.postListHead(a.thread)),
                            border = el.find("li.ds-tab-border"),
                            el.find("select").val(a.order).change(function (a) {
                                c.params.order = b.options.order = this.value,
                                    c.refetch()
                            })
                    },
                    reset: function () {
                        this.el.find("li.ds-tab-border").css("margin-left", this.el.find("a.ds-comments-tab-duoshuo").outerWidth() - 1 + "px")
                    }
                },
                m.Paginator = function (a) {
                    a && (this.el = a.el || d('<div class="ds-paginator"></div>'), a.collection && (this.collection = a.collection))
                },
                m.Paginator.prototype = {
                    reset: function (a) {
                        var b = this.collection.params.page || 1;
                        for (var c = 1, d = []; c <= a.pages; c++) d.push('<a data-page="' + c + '"' + (c == b ? ' class="ds-current"' : "") + ">" + c + "</a>");
                        this.el.html('<div class="ds-border"></div>' + d.join(" ")),
                            this.el[a.pages > 1 ? "show" : "hide"]()
                    },
                    render: function () {
                        var a = this,
                            b = a.collection;
                        return this.el.delegate("a", "click", function (c) {
                            return b.params.page = this.innerHTML,
                                b.refetch(),
                                a.el.find(".ds-current").removeClass("ds-current"),
                                d(this).addClass("ds-current"),
                                !1
                        }),
                            this
                    }
                },
                i.addSmilies = function (a, b) {
                    var c = i.smiliesTooltip;
                    c && c.el.find("ul.ds-smilies-tabs").append("<li><a>" + a + "</a></li>"),
                        i.smilies[a] = b
                },
                m.SmiliesTooltip = function (a) {
                    this.replybox = a
                },
                m.SmiliesTooltip.prototype = {
                    render: function () {
                        var a = this,
                            b = a.el = d(q.smiliesTooltip()),
                            e = a.replybox;
                        return b.click(function (a) {
                            a.stopPropagation()
                        }).find("ul.ds-smilies-tabs").delegate("a", "click", function (b) {
                            a.reset(this.innerHTML)
                        }),
                            b.find(".ds-smilies-container").delegate("img", "click", function (a) {
                                var b = e.el.find("textarea")[0],
                                    d = b.value;
                                if (c.selection) d += this.title;
                                else {
                                    var f = b.selectionStart + this.title.length;
                                    b.value = d.substring(0, b.selectionStart) + this.title + d.substring(b.selectionEnd),
                                        b.setSelectionRange(f, f)
                                }
                                e.hideSmilies(),
                                    b.focus()
                            }),
                            this
                    },
                    reset: function (a) {
                        var b = this.el.find("ul.ds-smilies-tabs");
                        b.find("a.ds-current").removeClass("ds-current"),
                            b.find("a").filter(function () {
                                return this.innerHTML == a
                            }).addClass("ds-current");
                        var c = '<ul style="">';
                        return d.each(p[a], function (b, d) {
                            var e;
                            e = i.APPPATH + i.STATIC + "/images/smilies/" + d,
                            a === "WordPress" && (b = " " + b + " "),
                                c += '<li><img src="' + e + '" title="' + h(b) + '" /></li>'
                        }),
                            c += "</ul>",
                            this.el.find(".ds-smilies-container").html(c),
                            this
                    }
                },
                l.PostList = function (a) {
                    a && (a.params && (this.params = a.params), a.embedThread && (this.embedThread = a.embedThread)),
                        this.el = d('<ul class="ds-comments"></ul>')
                },
                l.PostList.prototype = {
                    url: i.APPPATH + i.SERVER + "/post/list",
                    render: function () {
                        var a = this.embedThread;
                        return this.el.delegate("a.ds-post-delete", "click", function (b) {
                            if (confirm("确定要删除这条评论吗？")) {
                                var c = d(this).parents(".ds-post-self");
                                $.ajax({
                                    type: "POST",
                                    url: i.APPPATH + i.SERVER + "/post/delete",
                                    data: {postId: c.data("post-id")},
                                    success: function (data) {
                                        if (data.code == "1") {
                                            alert("删除评论操作失败！");
                                        } else {
                                            c.parent().fadeOut(200, function () {
                                                var b = d(this).parent(".ds-children"),
                                                    c = a.el.find(".ds-comments-tab-duoshuo span.ds-highlight");
                                                b.children("li:visible").length < 1 && b.siblings(".ds-children-control, .ds-collapse-padding").remove(),
                                                    c.html(parseInt(c.html()) - Math.max(b.find(".ds-post-self").length, 1))
                                            })
                                        }
                                    }
                                });
                            }
                            return !1
                        }).delegate("a.ds-post-reply", "click", function (b) {
                            var c = a.inlineReplybox;
                            if (d(this).hasClass("ds-reply-active")) c.el.fadeOut(200, function () {
                                d(this).hide()
                            }),
                                d(this).removeClass("ds-reply-active");
                            else {
                                var e = d(this).parents(".ds-post-self"),
                                    f = e.siblings("ul.ds-children");
                                f.length == 0 && (f = d('<ul class="ds-children"></ul>').insertAfter(e)),
                                    c ? c.replyButton.removeClass("ds-reply-active") : (c = a.inlineReplybox = new m.Replybox(a), c.render(!0).el.addClass("ds-inline-replybox").hide()),
                                    c.postList = f,
                                    c.replyButton = d(this).addClass("ds-reply-active"),
                                    c.el.find("[name=parentId]").val(e.data("post-id")),
                                    c.el[a.options.order == "asc" ? "insertAfter" : "insertBefore"](f).fadeIn(200).find("textarea").focus()
                            }
                        }).delegate("div.ds-children-control", "click", function (a) {
                            var b = d(this).parent().toggleClass("ds-collapsed");
                            d(this).children(".ds-expand").html('<span class="ds-ui-icon"></span>展开' + b.find(".ds-post-self").length + "条回复")
                        }),
                            this
                    },
                    reset: function (a) {
                        this.el.html(d.map(a, q.postWithChildren).join("")),
                            this.el.find(">.ds-post:first-child").addClass("ds-first")
                    },
                    refetch: function () {
                        var a = this,
                            b = d("#ds-indicator").fadeIn();
                        a.el.fadeTo(200, .5),
                            $.ajax({
                                url: a.url,
                                type: "GET",
                                data: a.params,
                                success: function (data) {
                                    if (data.code == "0") {
                                        a.reset(data.response),
                                            a.embedThread.paginator.reset(data.cursor),
                                            b.fadeOut(),
                                            a.el.fadeTo(200, 1)
                                    } else {
                                        alert("查询评论操作失败！");
                                    }
                                }
                            });

                    }
                },
                m.EmbedThread = function (a) {
                    this.el = a,
                        this.threadId = a.data("thread-id")
                },
                m.EmbedThread.prototype = {
                    reset: function () {
                    },
                    render: function () {
                        this.waitingEl = d('<div class="ds-waiting"></div>').appendTo(this.el).html(a.XMLHttpRequest ? '<img width="16" height="11" alt="..." src="data:image/gif;base64,R0lGODlhEAALAPQAAP///z2LqeLt8dvp7u7090GNqz2LqV+fuJ/F1IW2ycrf51aatHWswaXJ14i4ys3h6FmctUCMqniuw+vz9eHs8fb5+meku+Tu8vT4+cfd5bbT3tbm7PH2+AAAAAAAAAAAACH/C05FVFNDQVBFMi4wAwEAAAAh/hpDcmVhdGVkIHdpdGggYWpheGxvYWQuaW5mbwAh+QQJCwAAACwAAAAAEAALAAAFLSAgjmRpnqSgCuLKAq5AEIM4zDVw03ve27ifDgfkEYe04kDIDC5zrtYKRa2WQgAh+QQJCwAAACwAAAAAEAALAAAFJGBhGAVgnqhpHIeRvsDawqns0qeN5+y967tYLyicBYE7EYkYAgAh+QQJCwAAACwAAAAAEAALAAAFNiAgjothLOOIJAkiGgxjpGKiKMkbz7SN6zIawJcDwIK9W/HISxGBzdHTuBNOmcJVCyoUlk7CEAAh+QQJCwAAACwAAAAAEAALAAAFNSAgjqQIRRFUAo3jNGIkSdHqPI8Tz3V55zuaDacDyIQ+YrBH+hWPzJFzOQQaeavWi7oqnVIhACH5BAkLAAAALAAAAAAQAAsAAAUyICCOZGme1rJY5kRRk7hI0mJSVUXJtF3iOl7tltsBZsNfUegjAY3I5sgFY55KqdX1GgIAIfkECQsAAAAsAAAAABAACwAABTcgII5kaZ4kcV2EqLJipmnZhWGXaOOitm2aXQ4g7P2Ct2ER4AMul00kj5g0Al8tADY2y6C+4FIIACH5BAkLAAAALAAAAAAQAAsAAAUvICCOZGme5ERRk6iy7qpyHCVStA3gNa/7txxwlwv2isSacYUc+l4tADQGQ1mvpBAAIfkECQsAAAAsAAAAABAACwAABS8gII5kaZ7kRFGTqLLuqnIcJVK0DeA1r/u3HHCXC/aKxJpxhRz6Xi0ANAZDWa+kEAA7AAAAAAAAAAAA" style="margin:0 0 3px 5px"/>' : "...")
                    },
                    onload: function (posts, pager, options) {
                        var thread = this;
                        thread.waitingEl.hide(),
                            thread.options = options,
                        options.formPosition == "top" && thread.el.css("margin-top", "15px");
                        if (k.visitor.userId == 0) {
                            thread.toolbar = d(q.toolbar()).appendTo(thread.el);
                        } else thread.toolbar = d(q.toolbar()).appendTo(thread.el);
                        thread.replybox = new m.Replybox(thread),
                            thread.replybox.render().el.appendTo(thread.el),
                        options.message && thread.replybox.el.find("textarea").val(options.content).focus(),
                            thread.postListHead = new m.PostListHead(f, {
                                thread: k.currentThread,
                                order: options.order
                            }),
                            thread.postList = new l.PostList({
                                embedThread: thread,
                                params: {
                                    type: thread.el.data("type"),
                                    threadId: thread.el.data("thread-id"),
                                    order: options.order,
                                    limit: options.limit
                                }
                            }),
                            thread.paginator = new m.Paginator({
                                collection: thread.postList
                            }),
                            thread.postListHead.render(),
                            thread.postList.render().reset(posts),
                            thread.paginator.render().reset(pager),
                            thread.replybox.postList = thread.postList.el,
                            options.formPosition == "top" ? thread.el.append(thread.postListHead.el, thread.postList.el, thread.paginator.el) : (thread.toolbar).before(thread.postListHead.el, thread.postList.el, thread.paginator.el),
                            thread.postListHead.reset(),
                            d(q.indicator()).appendTo(c.body)
                    }
                },
                i.initEmbedThread = function (el) {
                    d.browser.msie && (el.addClass("ds-ie"), d.browser.version < 7 && el.addClass("ds-ie6"), d.browser.version < 9 && el.addClass("ds-ie8")),
                        j[0] = new m.EmbedThread(el),
                        j[0].render();
                    var params = [];
                    window.location.href && params.push("url=" + encodeURIComponent(a.location.href)),
                        params.push("threadId=" + encodeURIComponent(el.data("thread-id")))
                    d.each(["type", "posts", "limit", "order", "position"], function (a, c) {
                        el.data(c) && params.push(c + "=" + encodeURIComponent(el.data(c)))
                    }),
                        DUOSHUO.injectScript(i.APPPATH + i.SERVER + "/thread?" + params.join("&"));
                },
                i.init(),
                d(function () {
                    setInterval(function () {
                        d(".ds-time").each(function () {
                            this.innerHTML = i.elapsedTime(d(this).attr("datetime"))
                        })
                    }, 2e4),
                        i.init()
                })
        }),
        i.require(["JSON", "easyXDM"], function () {
            i.xhr = new easyXDM.Rpc({
                local: i.APPPATH + i.STATIC + "/js/cross/name.html",
                swf: i.APPPATH + i.STATIC + "/js/cross/easyxdm.swf",
                remote: i.APPPATH + i.STATIC + "/js/cross/index.html",
                remoteHelper: i.APPPATH + i.STATIC + "/js/cross/name.html"
            }, {
                remote: {
                    request: {}
                }
            })
        }),
        a.DUOSHUO = i
})(window)