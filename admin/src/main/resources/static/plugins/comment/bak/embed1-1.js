(function (a, b) {
    var c = window.document,
        d = document.getElementsByTagName("head")[0] || document.getElementsByTagName("body")[0],
        e = document.createElement("input"),
        f = window.encodeURI,
        g = window.navigator.userAgent,
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
            DOMAIN: "duoshuo.com",
            REMOTE: 'http://localhost:8080/metp/resources/ui/common/component/comment',//"http://duoshuo.com",
            STATIC: "http://static.duoshuo.com",
            EMBED_STYLESHEET: "/styles/embed.css?0.2.css",
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
                "#ds-thread": !0
            },
            sourceName: {
                weibo: "新浪微博",
                qq: "QQ",
                qzone: "QQ空间",
                qqt: "腾讯微博",
                renren: "人人网",
                douban: "豆瓣网",
                netease: "网易微博",
                kaixin: "开心网",
                sohu: "搜狐微博",
                baidu: "百度"
            },
            serviceNames: {
                weibo: "微博",
                qq: "QQ",
                douban: "豆瓣",
                renren: "人人",
                netease: "网易",
                kaixin: "开心",
                sohu: "搜狐",
                baidu: "百度"
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
            escKey: function (a) {
                if (a.which == 27) return i.dialog && i.dialog.close(),
                    !1
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
                if (typeof a == "string") i.loaded[a] ? b() : i.injectScript(i.STATIC + i.libs[a], function () {
                    i.loaded[a] = !0,
                        b()
                });
                else if (typeof a == "object") {
                    var c, d = !0;
                    for (c = 0; c < a.length; c++) (function (e) {
                        if (i.loaded[a[c]]) return;
                        d = !1,
                            i.injectScript(i.STATIC + i.libs[e], function () {
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
            init: function (thread_id) {
                thread_id && (i.selectors[thread_id] = !0);
                jQuery && (jQuery.isReady || !jQuery.browser.msie) && jQuery.each(i.selectors, function (thread_id, c) {
                    var el = jQuery(thread_id);
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
            WordPress: {
                ":mrgreen:": "icon_mrgreen.gif",
                ":neutral:": "icon_neutral.gif",
                ":twisted:": "icon_twisted.gif",
                ":arrow:": "icon_arrow.gif",
                ":shock:": "icon_eek.gif",
                ":smile:": "icon_smile.gif",
                ":???:": "icon_confused.gif",
                ":cool:": "icon_cool.gif",
                ":evil:": "icon_evil.gif",
                ":grin:": "icon_biggrin.gif",
                ":idea:": "icon_idea.gif",
                ":oops:": "icon_redface.gif",
                ":razz:": "icon_razz.gif",
                ":roll:": "icon_rolleyes.gif",
                ":wink:": "icon_wink.gif",
                ":cry:": "icon_cry.gif",
                ":eek:": "icon_surprised.gif",
                ":lol:": "icon_lol.gif",
                ":mad:": "icon_mad.gif",
                ":sad:": "icon_sad.gif",
                "8-)": "icon_cool.gif",
                "8-O": "icon_eek.gif",
                ":-(": "icon_sad.gif",
                ":-)": "icon_smile.gif",
                ":-?": "icon_confused.gif",
                ":-D": "icon_biggrin.gif",
                ":-P": "icon_razz.gif",
                ":-o": "icon_surprised.gif",
                ":-x": "icon_mad.gif",
                ":-|": "icon_neutral.gif",
                ";-)": "icon_wink.gif",
                "8)": "icon_cool.gif",
                "8O": "icon_eek.gif",
                ":(": "icon_sad.gif",
                ":)": "icon_smile.gif",
                ":?": "icon_confused.gif",
                ":D": "icon_biggrin.gif",
                ":P": "icon_razz.gif",
                ":o": "icon_surprised.gif",
                ":x": "icon_mad.gif",
                ":|": "icon_neutral.gif",
                ";)": "icon_wink.gif",
                ":!:": "icon_exclaim.gif",
                ":?:": "icon_question.gif"
            }
        },
        q = i.templates = {
            authorAnchor: function (a) {
                var b = a.author_name || a.author.name,
                    c = a.author_url || a.author.url;
                return c ? '<a rel="author" target="_blank" href="' + f(c) + '">' + h(b) + "</a>" : h(b)
            },
            post: function (a) {
                var b = '<img src="' + f(a.author && a.author.avatar_url || i.STATIC + "/images/noavatar_default.png") + '" />',
                    c = '<span class="ds-time" datetime="' + a.created_at + '" title="' + i.fullTime(a.created_at) + '">' + i.elapsedTime(a.created_at) + "</span>";
                return '<div class="ds-post-self' + (a.vote > 0 ? " ds-post-liked" : "") + '" data-post-id="' + a.post_id + '" data-thread-id="' + a.thread_id + '">' + '<div class="ds-post-color"></div>' + '<div class="ds-post-main">' + '<div class="ds-avatar">' + (a.author_url ? '<a href="' + f(a.author_url) + '" rel="nofollow" target="_blank">' + b + "</a>" : b) + "</div>" + '<div class="ds-comment-body">' + '<div class="ds-comment-header">' + (a.author ? '<a class="ds-user-name ds-highlight" href="' + f(a.author.url || "javascript:void(0)") + '" rel="nofollow" target="_blank">' + h(a.author.name) + "</a>" : '<span class="ds-user-name">' + h(a.author_name) + "</span>") + "</div>" + "<p>" + a.message + "</p>" + '<div class="ds-comment-footer">' + (a.url ? '<a href="' + a.url + '" target="_blank" rel="nofollow">' + c + "</a>" : c) + (a.source != "weibo" ? '<a class="ds-post-reply"><span class="ds-ui-icon"></span>回复</a>' : "") + (a.privileges["delete"] ? ' <a class="ds-post-delete"><span class="ds-ui-icon"></span> 删除</a>' : "") + "</div>" + "</div>" + "</div>" + "</div>"
            },
            replybox: function (a, b) {
                var c = k.visitor,
                    d = [],
                    e = "",
                    f = k.visitor.repostOptions.active;
                for (service in c.repostOptions) service !== "active" && (c.repostOptions[service] && d.push(service), e += q.serviceIcon(service, f, c.repostOptions[service]));
                return '<div class="ds-replybox"><form method="post" action="" onsubmit="return false;"><input type="hidden" name="thread_id" value="' + a + '" />' + '<input type="hidden" name="parent_id" value="" />' + (c.user_id ? "" : '<input type="hidden" name="author_name" value="' + h(c.name) + '" /><input type="hidden" name="author_email" value="' + h(c.email) + '" /><input type="hidden" name="author_url" value="' + h(c.url) + '" />') + '<div class="ds-replybox-main">' + '<div class="ds-textarea-wrapper">' + '<textarea name="message" title="Ctrl+Enter快捷提交" placeholder="说点什么吧 ..."></textarea>' + '<div class="ds-hidden-text"></div>' + "</div>" +
                    '<div class="ds-post-toolbar">' + '<div class="ds-post-options">' + '<a class="ds-toolbar-button ds-add-emote"></a><span class="ds-sync" style="color: #999;">提示信息</span>' + "</div>" + '<button class="ds-post-button" type="submit">发 布</button>' + "</div>" + "</div>" + "</form>" + "</div>"
            },
            postListHead: function (a) {
                return '<div class="ds-comments-info"><select class="ds-sort"><option value="desc">从新到旧排序</option><option value="asc">从旧到新排序</option></select><ul class="ds-comments-tabs"><li class="ds-tab"><a class="ds-comments-tab-duoshuo ds-current"><span class="ds-highlight">' + a.comments + "</span>条评论</a></li>" + '<li class="ds-tab-border ' + '"></li>' + "</ul>" + "</div>";
            },
            toolbar: function (a) {
                return '<div class="ds-toolbar"><div class="ds-border-highlight"></div><div class="ds-visitor"><span class="ds-visitor-avatar"><img src="' + f(k.visitor.avatar_url) + '"/>' + "</span>" + '<span class="ds-visitor-name">' + h(k.visitor.name) + "</span>" + "</div>" + "</div>"
            },
            indicator: o('<div id="ds-indicator"><img src="' + i.STATIC + '/images/loading.gif"/></div>')
        };
    i.loaded.jQuery && (i.jQuery = a.jQuery),
        i.require("jQuery", function () {
            var d = i.jQuery;
            d.extend(q, {
                postWithChildren: function (a) {
                    var b = '<li class="ds-post" data-post-id="' + a.post_id + '">' + q.post(a);
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
                        function t(a) {
                            a.data("submitting", !0),
                                a.find(".ds-post-button").addClass("ds-waiting").html("正在发布")[0].disabled = !0
                        }

                        function u(a) {
                            a.data("submitting", !1),
                                a.find(".ds-post-button").removeClass("ds-waiting").html("发 布")[0].disabled = !1
                        }

                        var e = this,
                            f = e.embedThread,
                            g = e.el = d(q.replybox(f.threadId, b)),
                            h = g.find("form"),
                            j = h.find("input[type=checkbox]")[0],
                            l = h.find("span.ds-connected-sites a"),
                            n = g.find("div.ds-hidden-text"),
                            o = h.find("textarea").keydown(function (a) {
                                if (a.ctrlKey && a.which == 13 || a.which == 10) h.data("submitting") || h.trigger("submit")
                            }).keyup(function (a) {
                                n.html(this.value.replace(/\n/g, "<br/>")),
                                    d(this).height(Math.max(54, n.height() + 27))
                            }),
                            p = g.find(".ds-add-emote").click(function (a) {
                                var b = i.smiliesTooltip;
                                if (p.toggleClass("ds-expanded").hasClass("ds-expanded")) {
                                    b || (b = i.smiliesTooltip = new m.SmiliesTooltip, b.render().reset("WordPress"));
                                    var g = e.el.offset(),
                                        h = f.el.offset();
                                    b.el.appendTo(f.el).css({
                                        top: g.top - h.top + e.el.outerHeight() + 5 + "px",
                                        left: g.left - h.left + "px"
                                    }).show(),
                                        d(c.body).click(r)
                                } else r(a);
                                return !1
                            }),
                            r = function (a) {
                                p.removeClass("ds-expanded"),
                                    i.smiliesTooltip.el.hide(),
                                    d(c.body).unbind("click", r)
                            };
                        if (!i.support.placeholder) {
                            var s = o.attr("placeholder");
                            o.val(s).focus(function () {
                                this.value === s && (this.value = "")
                            }).blur(function () {
                                this.value === "" && (this.value = s)
                            })
                        }
                        var v = function () {
                            t(h),
                                i.xhr.request({
                                    url: "/api/posts/create.json",
                                    method: "POST",
                                    data: i.toJSON(d(h))
                                }, i.checkResponse(function (b) {
                                    var c = f.options.order == "asc",
                                        i = f.options.formPosition == "top",
                                        j = d(q.postWithChildren(b));
                                    j = j.hide()[c ? "appendTo" : "prependTo"](e.postList);
                                    var k = j.find(".ds-post-color").show();
                                    j.slideDown(function () {
                                        k.delay(1e3).fadeOut()
                                    }),
                                    i == c && d(a).scrollTop(j.offset().top - 200);
                                    var l = f.el.find(".ds-comments-tab-duoshuo span.ds-highlight");
                                    l.html(parseInt(l.html()) + 1),
                                        o.val("").height(54),
                                        u(h),
                                    g.hasClass("ds-inline-replybox") && (g.hide(), e.replyButton.removeClass("ds-reply-active"))
                                }, function (a) {
                                    u(h)
                                }), function (a) {
                                    u(h)
                                })
                        };
                        h.submit(function () {
                            var a = d.trim(h[0].message.value);
                            return a == "" || !i.support.placeholder && a == o.attr("placeholder") ? (alert("您还没写内容呢"), !1) : (k.visitor.user_id == 0 ? (alert("您没有登录，请登陆！"), !1) : v(), !1)
                        });
                        var w = function (a) {
                            var b = d(a).attr("data-service");
                            return d(a).hasClass("ds-" + b + "-active") ? b : null
                        };
                        return l.click(function () {
                            var a = d(this);
                            service = a.attr("data-service"),
                                a.toggleClass("ds-" + service + "-active"),
                                j.value = d.map(l, w).join(","),
                                j.checked = j.value != ""
                        }),
                            d(j).change(function () {
                                var a = this.checked;
                                l.each(function () {
                                    d(this)[a ? "addClass" : "removeClass"]("ds-" + d(this).attr("data-service") + "-active")
                                }),
                                    this.value = d.map(l, w).join(",")
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
                m.SmiliesTooltip = function () {
                },
                m.SmiliesTooltip.prototype = {
                    render: function () {
                        var a = this,
                            b = a.el = d(q.smiliesTooltip());
                        return b.click(function (a) {
                            a.stopPropagation()
                        }).find("ul.ds-smilies-tabs").delegate("a", "click", function (c) {
                            a.reset(this.innerHTML),
                                b.find("ul.ds-smilies-tabs a.ds-current").removeClass("ds-current"),
                                d(this).addClass("ds-current")
                        }),
                            this
                    },
                    reset: function (a) {
                        var b = '<ul style="">';
                        d.each(p[a], function (c, d) {
                            var e;
                            a.indexOf("微博") === 0 ? e = "http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/" + d.replace("_org", "_thumb") : e = i.STATIC + "/images/smilies/" + d,
                                b += '<li><img src="' + e + '" title="' + h(c) + '" /></li>'
                        }),
                            b += "</ul>";
                        var c = d(b).delegate("img", "click", function (a) {
                            alert(this.title)
                        });
                        return this.el.find(".ds-smilies-container").html(b),
                            this
                    }
                },
                l.PostList = function (a) {
                    a && (a.params && (this.params = a.params), a.embedThread && (this.embedThread = a.embedThread)),
                        this.el = d('<ul class="ds-comments"></ul>')
                },
                l.PostList.prototype = {
                    url: "/api/posts/list.json",
                    render: function () {
                        var a = this.embedThread;
                        return this.el.delegate("a.ds-post-delete", "click", function (b) {
                            if (confirm("确定要删除这条评论吗？")) {
                                var c = d(this).parents(".ds-post-self");
                                i.xhr.request({
                                    url: "/api/posts/remove.json",
                                    method: "POST",
                                    data: {
                                        post_id: c.data("post-id")
                                    }
                                }, i.checkResponse(function (a) {
                                })),
                                    c.parent().fadeOut(200, function () {
                                        var b = d(this).parent(".ds-children"),
                                            c = a.el.find(".ds-comments-tab-duoshuo span.ds-highlight");
                                        b.children("li:visible").length < 1 && b.siblings(".ds-children-control, .ds-collapse-padding").remove(),
                                            c.html(parseInt(c.html()) - Math.max(b.find(".ds-post-self").length, 1))
                                    })
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
                                    c.el.find("[name=parent_id]").val(e.data("post-id")),
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
                            i.xhr.request({
                                url: a.url,
                                method: "GET",
                                data: a.params
                            }, i.checkResponse(function (c, d) {
                                a.reset(c),
                                    a.embedThread.paginator.reset(d),
                                    b.fadeOut(),
                                    a.el.fadeTo(200, 1)
                            }))
                    }
                },
                m.EmbedThread = function (el) {
                    this.el = el,
                        this.threadId = el.data("thread-id")
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
                        if (k.visitor.user_id == 0) {
                            thread.toolbar = d(q.toolbar()).appendTo(thread.el);
                        } else thread.toolbar = d(q.toolbar()).appendTo(thread.el);
                        thread.replybox = new m.Replybox(thread),
                            thread.replybox.render().el.appendTo(thread.el),
                        options.message && thread.replybox.el.find("textarea").val(options.message).focus(),
                            thread.postListHead = new m.PostListHead(f, {
                                thread: k.currentThread,
                                order: options.order
                            }),
                            thread.postList = new l.PostList({
                                embedThread: thread,
                                params: {
                                    source: "duoshuo",
                                    thread_id: thread.el.data("thread-id"),
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
                        d.each(["thread-id", "limit", "order", "title", "type"], function (a, c) {
                            el.data(c) && params.push(c.replace("-", "_") + "=" + encodeURIComponent(el.data(c)))
                        }),
                        DUOSHUO.injectScript(i.REMOTE + "/thread.js?" + params.join("&"));
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
        i.injectStylesheet(i.STATIC + i.EMBED_STYLESHEET),
        i.require(["JSON", "easyXDM"], function () {
            i.xhr = new easyXDM.Rpc({
                local: "/name.html",
                swf: i.REMOTE + "/easyxdm.swf",
                remote: i.REMOTE + "/cors/index.html",
                remoteHelper: i.REMOTE + "/name.html"
            }, {
                remote: {
                    request: {}
                }
            })
        }),
        a.DUOSHUO = i
})(window)