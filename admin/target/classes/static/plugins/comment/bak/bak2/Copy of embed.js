(function (a, b) {
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
            openDialog: function (a, b) {
                var c = a[0].author_email.value,
                    d = a[0].author_name.value,
                    e = a[0].author_url.value,
                    f = '<div class="ds-dialog ds-dialog-login"><div class="ds-dialog-inner"><div class="ds-dialog-body"><div class="ds-dialog-left"><h2>用社交帐号登录</h2>' + q.serviceList() + q.additionalServices() + "</div>" + '<div class="ds-dialog-right">' + "<h2>作为游客留言</h2>" + "<form>" + '<div class="ds-input">' + '<input type="text" name="name" id="ds-dialog-name" value="' + d + '" required />' + '<label for="ds-dialog-name">名字</label>' + "</div>" + '<div class="ds-input">' + '<input type="text" name="email" id="ds-dialog-email" value="' + c + '" required />' + '<label for="ds-dialog-email">邮箱</label>' + "</div>" + '<div class="ds-input">' + '<input type="text" name="url" id="ds-dialog-url" value="' + (e || "http://") + '" />' + '<label for="ds-dialog-url">网址</label>' + "</div>" + '<button type="submit">发 布</button>' + "</form>" + "</div>" + "</div>" + '<div class="ds-dialog-footer">' + '<a href="http://duoshuo.com" target="_blank" class="ds-logo"></a><span>社会化评论框</span>' + "</div>" + '<a class="ds-dialog-close" href="javascript:void(0)"></a>' + "</div>" + "</div>";
                typeof i.dialog == "object" && i.dialog.el.remove();
                var g = i.dialog = new m.Dialog(f);
                g.open();
                var h = g.el.find("form");
                h.submit(function (c) {
                    var d = h.find("input[name=name]").val(),
                        f = h.find("input[name=email]").val();
                    return e = h.find("input[name=url]").val(),
                        f.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/) ? (a.find("input[name=author_name]").val(d), a.find("input[name=author_email]").val(f), a.find("input[name=author_url]").val(e), b(), g.close(), !1) : (alert("请输入一个有效的email地址."), !1)
                }),
                    h.find("input[name=name]")[0].focus()
            },
            bindMoreAccountDialog: function () {
                var a = '<div class="ds-dialog ds-dialog-bind-more" style="width: 300px;"><div class="ds-dialog-inner"><div class="ds-dialog-body"><h2>绑定更多帐号</h2>' + q.serviceList() + q.additionalServices() + '<div style="clear:both"></div>' + "</div>" + '<div class="ds-dialog-footer">' + '<a href="http://duoshuo.com" target="_blank" class="ds-logo"></a><span>社会化评论框</span>' + "</div>" + '<a class="ds-dialog-close" href="javascript:void(0)"></a>' + "</div>" + "</div>";
                typeof i.dialog == "object" && i.dialog.el.remove(),
                    i.dialog = (new m.Dialog(a)).open()
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
            init: function (a) {
                a && (i.selectors[a] = !0);
                var b = i.jQuery;
                b && (b.isReady || !b.browser.msie) && b.each(i.selectors, function (a, c) {
                    var d = b(a);
                    if (d.length == 0 || d.data("initialized")) return;
                    d.data("initialized", !0),
                        i.initEmbedThread(d)
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
                return '<div class="ds-post-self' + (a.vote > 0 ? " ds-post-liked" : "") + '" data-post-id="' + a.post_id + '" data-thread-id="' + a.thread_id + '">' + '<div class="ds-post-color"></div>' + '<div class="ds-post-main">' + '<div class="ds-avatar">' + (a.author_url ? '<a href="' + f(a.author_url) + '" rel="nofollow" target="_blank">' + b + "</a>" : b) + (i.sourceName[a.source] ? q.serviceIcon(a.source) : "") + "</div>" + '<div class="ds-comment-body">' + '<div class="ds-comment-header">' + (a.author ? '<a class="ds-user-name ds-highlight" href="' + f(a.author.url || "javascript:void(0)") + '" rel="nofollow" target="_blank">' + h(a.author.name) + "</a>" : '<span class="ds-user-name">' + h(a.author_name) + "</span>") + "</div>" + "<p>" + a.message + "</p>" + '<div class="ds-comment-footer">' + (a.url ? '<a href="' + a.url + '" target="_blank" rel="nofollow">' + c + "</a>" : c) + (a.source != "weibo" ? '<a class="ds-post-reply"><span class="ds-ui-icon"></span>回复</a>' : "") + (a.source != "weibo" && k.visitor.user_id ? '<a class="ds-post-likes"><span class="ds-post-like"><span class="ds-ui-icon"></span></span><span class="ds-post-unlike"><span class="ds-ui-icon"></span></span> 赞 <span class="ds-post-like-count">' + (a.likes > 0 ? a.likes : "") + "</span></a>" : "") + (a.source != "weibo" ? ' <a class="ds-post-report"><span class="ds-ui-icon"></span> 举报</a>' : "") + (a.source != "weibo" && a.privileges["delete"] ? ' <a class="ds-post-delete"><span class="ds-ui-icon"></span> 删除</a>' : "") + "</div>" + "</div>" + "</div>" + "</div>"
            },
            replybox: function (a, b) {
                var c = k.visitor,
                    d = [],
                    e = "",
                    f = k.visitor.repostOptions.active;
                for (service in c.repostOptions) service !== "active" && (c.repostOptions[service] && d.push(service), e += q.serviceIcon(service, f, c.repostOptions[service]));
                return '<div class="ds-replybox"><form method="post" action="" onsubmit="return false;"><input type="hidden" name="thread_id" value="' + a + '" />' + '<input type="hidden" name="parent_id" value="" />' + (c.user_id ? "" : '<input type="hidden" name="author_name" value="' + h(c.name) + '" /><input type="hidden" name="author_email" value="' + h(c.email) + '" /><input type="hidden" name="author_url" value="' + h(c.url) + '" />') + '<div class="ds-replybox-main">' + '<div class="ds-textarea-wrapper">' + '<textarea name="message" title="Ctrl+Enter快捷提交" placeholder="说点什么吧 ..."></textarea>' + '<div class="ds-hidden-text"></div>' + "</div>" + '<div class="ds-post-toolbar">' + '<div class="ds-post-options">' + (c.user_id ? e ? '<span class="ds-sync"><input id="ds-sync-checkbox' + (b ? "-inline" : "") + '" type="checkbox" name="repost" ' + (c.repostOptions.active ? 'checked="checked" ' : "") + 'value="' + d.join(",") + '"> <label for="ds-sync-checkbox' + (b ? "-inline" : "") + '">同时分享到:</label> ' + e + "</span>" : "" : '<span class="ds-sync" style="color: #999;">不想登录？直接点击发布即可作为游客留言。</span>') + "</div>" + '<button class="ds-post-button" type="submit">发 布</button>' + "</div>" + "</div>" + "</form>" + "</div>"
            },
            serviceIcon: function (a, b, c) {
                return a == "active" ? "" : '<span class="ds-connected-sites"><a class="ds-service-icon ds-' + a + (b ? c ? " ds-" + a + "-active" : "" : b === !1 ? "" : " ds-" + a + "-active") + '" data-service="' + a + '" title="' + i.sourceName[a] + '"></a></span>'
            },
            loginButtons: function (a) {
                return '<div class="ds-login-buttons"><div class="ds-border-highlight"></div><p>使用社交帐号登录:</p>' + q.serviceList() + q.additionalServices() + "</div>"
            },
            postListHead: function (a) {
                var b = k.site.show_weibo && a.weibo_reposts;
                return '<div class="ds-comments-info"><select class="ds-sort"><option value="desc">从新到旧排序</option><option value="asc">从旧到新排序</option></select><ul class="ds-comments-tabs"><li class="ds-tab"><a class="ds-comments-tab-duoshuo ds-current"><span class="ds-highlight">' + a.comments + "</span>条评论</a></li>" + (b ? '<li class="ds-tab"><a class="ds-comments-tab-weibo"><span class="ds-highlight">' + a.weibo_reposts + "</span>条微博</a></li>" : "") + '<li class="ds-tab-border ' + (b ? "" : "ds-no-weibo") + '"></li>' + "</ul>" + "</div>"
            },
            toolbar: function (a) {
                return '<div class="ds-toolbar"><div class="ds-border-highlight"></div><div class="ds-visitor"><span class="ds-visitor-avatar"><img src="' + f(k.visitor.avatar_url) + '"/>' + "</span>" + '<span class="ds-visitor-name">' + h(k.visitor.name) + "</span>" + '<a class="ds-unread-comments-count"></a>' + '<span><a rel="nofollow" href="http://duoshuo.com/logout">登出</a></span>' + "</div>" + '<div class="ds-account-control">' + '<a><span class="ds-ui-icon"></span> <span>账号管理</span></a>' + "</div>" + "</div>"
            },
            copyRights: [function (a) {
                return h(a.name) + "正在使用多说"
            },
                o("多说"), o("Powered by 多说")],
            poweredBy: function (a) {
                return '<p class="ds-powered-by"><a href="http://duoshuo.com" target="_blank" rel="nofollow">' + q.copyRights[a.copyright_template](a) + "</a></p>"
            },
            likePanel: function (a) {
                return a.likes ? '<span class="ds-highlight">' + a.likes + "</span> 人喜欢" : ""
            },
            indicator: o('<div id="ds-indicator"><img src="' + i.STATIC + '/images/loading.gif"/></div>'),
            serviceList: function () {
                return '<ul class="ds-service-list">' + q.loginItem("weibo") + q.loginItem("qq") + q.loginItem("renren") + q.loginItem("douban") + q.loginItem("kaixin") + '<li><a class="ds-more-services">更多?</a></li>' + "</ul>"
            },
            additionalServices: function () {
                return '<ul class="ds-service-list ds-additional-services">' + q.loginItem("netease") + q.loginItem("sohu") + q.loginItem("baidu") + "</ul>"
            },
            loginItem: function (a) {
                return '<li><span class="ds-connected-sites"><a href="' + i.REMOTE + "/login/" + a + '/" rel="nofollow" class="ds-service-icon ds-' + a + " ds-" + a + '-active"></a>' + "</span>" + '<a href="' + i.REMOTE + "/login/" + a + '/" rel="nofollow">' + i.serviceNames[a] + "</a>" + (k.visitor.connected_services[a] ? '<span class="ds-ui-icon"></span>' : "") + "</li>"
            }
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
                            return a == "" || !i.support.placeholder && a == o.attr("placeholder") ? (alert("您还没写内容呢"), !1) : (k.visitor.user_id == 0 ? i.openDialog(h, v) : v(), !1)
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
                m.Dialog = function (a, c) {
                    this.options = d.extend({
                        width: 600
                    }, c),
                        this.el = d("#ds-dialog-wrapper"),
                    this.el.length == 0 && (this.el = d('<div id="ds-dialog-wrapper"></div>'), d.browser.msie && (this.el.addClass("ds-ie"), d.browser.version < 7 && this.el.addClass("ds-ie6"))),
                    a !== b && (this.el.append(a), this.el.find("a.ds-dialog-close").click(function () {
                        i.dialog.close()
                    }))
                },
                m.Dialog.prototype = {
                    open: function () {
                        return this.el.hide().appendTo(c.body).fadeIn(),
                        d.browser.msie && d.browser.version < 7 && (this.el.css("top", d(a).scrollTop() + 100), d("#ds-thread").find("select.ds-sort").css("visibility", "hidden")),
                            this.place(),
                            d(c).bind("keydown", i.escKey),
                            this.el.show(),
                            this
                    },
                    place: function () {
                        this.el.css({})
                    },
                    close: function (a) {
                        d(c).unbind("keydown", i.escKey);
                        var b = this.embedThread;
                        this.el.fadeOut(function () {
                            d(this).remove(),
                            d.browser.msie && d.browser.version < 7 && d("#ds-thread").find("select.ds-sort").css("visibility", "visible")
                        })
                    },
                    hide: function (a) {
                    },
                    setBody: function (a) {
                        this.el.find(".ds-dialog-body").html(a)
                    },
                    setFooter: function (a) {
                        this.el.find(".ds-dialog-footer").html(a)
                    }
                },
                q.likeTooltip = function () {
                    var a = {
                            qzone: "QQ空间",
                            weibo: "新浪微博",
                            qqt: "腾讯微博",
                            renren: "人人网",
                            kaixin: "开心网",
                            douban: "豆瓣网",
                            baidu: "百度搜藏",
                            netease: "网易微博",
                            sohu: "搜狐微博"
                        },
                        b = [];
                    d.each(a, function (a, c) {
                        b.push('<li><a class="ds-share-to-' + a + " ds-service-link ds-" + a + '-active">' + c + "</a></li>")
                    });
                    var c = Math.ceil(b.length / 2);
                    return '<div class="ds-like-tooltip"><p>很高兴你能喜欢，分享一下吧：</p><ul>' + b.slice(0, c).join("") + "</ul>" + "<ul>" + b.slice(c).join("") + "</ul>" + '<p class="ds-like-tooltip-footer"><a class="ds-like-tooltip-close">算了</a></p>' + "</div>"
                },
                m.Meta = function (a) {
                    this.embedThread = a
                },
                m.Meta.prototype = {
                    render: function () {
                        var e = this,
                            f = e.embedThread,
                            g = e.el = d('<div class="ds-meta"><a class="ds-like-thread-button' + (k.currentThread.user_vote > 0 ? " ds-thread-liked" : "") + '"><span class="ds-ui-icon"></span>' + ' <span class="ds-thread-like-text">' + (k.currentThread.user_vote > 0 ? "已喜欢" : "喜欢") + '</span><span class="ds-thread-cancel-like">取消喜欢</span></a>' + '<span class="ds-like-panel"></span>' + "</div>"),
                            h = g.find(".ds-like-thread-button").click(function (j) {
                                var l = h.hasClass("ds-thread-liked");
                                i.xhr.request({
                                    url: "/api/threads/vote.json",
                                    method: "POST",
                                    data: {
                                        thread_id: f.threadId,
                                        vote: l ? 0 : 1
                                    }
                                }, i.checkResponse(function (a) {
                                    k.currentThread = a.thread,
                                        e.resetLikePanel()
                                })),
                                    h.toggleClass("ds-thread-liked"),
                                    h.find(".ds-thread-like-text").text(l ? "喜欢" : "已喜欢");
                                var m = function (a) {
                                    e.tooltip.detach(),
                                        d(c.body).unbind("click", m)
                                };
                                return l ? e.tooltip && m(j) : (e.tooltip === b && (e.tooltip = d(q.likeTooltip(k.currentThread)).click(function (a) {
                                    a.stopPropagation()
                                }).delegate("a", "click", function (b) {
                                    switch (this.className) {
                                        case "ds-like-tooltip-close":
                                            m(b);
                                            break;
                                        default:
                                            var c = this.className.match(/ds\-share\-to\-(\w+)/)[1];
                                            a.open("http://" + k.site.short_name + "." + i.DOMAIN + "/share-proxy/?service=" + c + "&thread_id=" + f.threadId, "_blank", "height=500,width=600,top=0,left=0,toolbar=no,menubar=no,resizable=yes,location=yes,status=no")
                                    }
                                    return !1
                                })), e.tooltip.appendTo(f.el).css({
                                    top: g.position().top + g.outerHeight() - 4 + "px",
                                    left: 0
                                }).show(), d(c.body).click(m)),
                                    !1
                            });
                        return e.resetLikePanel(),
                        k.visitor.user_id || g.hide(),
                            e
                    },
                    resetLikePanel: function () {
                        this.el.find(".ds-like-panel").html(q.likePanel(k.currentThread))
                    }
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
                            dstab = el.find("a.ds-comments-tab-duoshuo"),
                            wbtab = el.find("a.ds-comments-tab-weibo"),
                            border = el.find("li.ds-tab-border"),
                            el.find("select").val(a.order).change(function (a) {
                                c.params.order = b.options.order = this.value,
                                    c.refetch()
                            }),
                            dstab.click(function (a) {
                                wbtab.removeClass("ds-current"),
                                    dstab.addClass("ds-current"),
                                    c.params.source = "duoshuo",
                                    c.refetch(),
                                    b.replybox.el.show()
                            }),
                            wbtab.click(function (a) {
                                dstab.removeClass("ds-current"),
                                    wbtab.addClass("ds-current"),
                                    c.params.source = "weibo",
                                    c.refetch(),
                                    b.replybox.el.hide()
                            })
                    },
                    reset: function () {
                        this.el.find("li.ds-tab-border").css("margin-left", this.el.find("a.ds-comments-tab-duoshuo").outerWidth() + this.el.find("a.ds-comments-tab-weibo").outerWidth() - 1 + "px")
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
                        return this.el.delegate("a.ds-post-likes", "click", function (a) {
                            var b = d(this).parents(".ds-post-self"),
                                c = d(this).find(".ds-post-like-count"),
                                e = b.hasClass("ds-post-liked"),
                                f = {
                                    post_id: b.data("post-id"),
                                    vote: e ? 0 : 1
                                };
                            return i.xhr.request({
                                url: "/api/posts/vote.json",
                                method: "POST",
                                data: f
                            }, i.checkResponse(function (a) {
                            })),
                                c.html((c.html() == "" ? 0 : parseInt(c.html())) + (e ? -1 : 1)),
                                b[e ? "removeClass" : "addClass"]("ds-post-liked"),
                                !1
                        }).delegate("a.ds-post-delete", "click", function (b) {
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
                        }).delegate("a.ds-post-report", "click", function (a) {
                            if (confirm("确定要举报这条评论吗？")) {
                                var b = d(this).parents(".ds-post-self");
                                i.xhr.request({
                                    url: "/api/posts/report.json",
                                    method: "POST",
                                    data: {
                                        post_id: b.data("post-id")
                                    }
                                }, i.checkResponse(function (a) {
                                })),
                                    alert("感谢您的反馈！")
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
                i.unreadCommentsDialog = function () {
                    var a = '<div class="ds-dialog" style="width: 450px;"><div class="ds-dialog-inner"><div class="ds-dialog-body"><h2>你有' + k.unreadComments.length + "条新回复</h2>" + "<ul>" + d.map(k.unreadComments, function (a) {
                        return "<li>" + q.authorAnchor(a) + ' 在 <a href="' + a.thread.url + '#comments" target="_blank">' + a.thread.title + "</a> 中回复你</li>"
                    }).join("") + "</ul>" + "</div>" + '<div class="ds-dialog-footer">' + '<a href="http://duoshuo.com" target="_blank" class="ds-logo"></a><span>社会化评论框</span>' + "</div>" + '<a class="ds-dialog-close" href="javascript:void(0)"></a>' + "</div>" + "</div>";
                    typeof i.dialog == "object" && i.dialog.el.remove(),
                        i.dialog = new m.Dialog(a),
                        i.dialog.open()
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
                    onload: function (a, b, e) {
                        var f = this;
                        f.waitingEl.hide(),
                            f.options = e,
                        e.formPosition == "top" && f.el.css("margin-top", "15px"),
                        k.site.like_thread_enabled && (f.meta = new m.Meta(f), f.meta.render().el.appendTo(f.el));
                        if (k.visitor.user_id == 0) {
                            f.loginButtons = d(q.loginButtons()).appendTo(f.el);
                            var g = f.loginButtons.find("ul.ds-additional-services");
                            f.loginButtons.find("a.ds-more-services").click(function () {
                                g.toggle()
                            })
                        } else f.toolbar = d(q.toolbar()).appendTo(f.el),
                            f.toolbar.find(".ds-account-control a").click(i.bindMoreAccountDialog),
                            f.toolbar.find(".ds-unread-comments-count").click(i.unreadCommentsDialog);
                        f.replybox = new m.Replybox(f),
                            f.replybox.render().el.appendTo(f.el),
                        e.message && f.replybox.el.find("textarea").val(e.message).focus(),
                            f.postListHead = new m.PostListHead(f, {
                                thread: k.currentThread,
                                order: e.order
                            }),
                            f.postList = new l.PostList({
                                embedThread: f,
                                params: {
                                    source: "duoshuo",
                                    thread_id: f.el.data("thread-id"),
                                    order: e.order,
                                    limit: e.limit
                                }
                            }),
                            f.paginator = new m.Paginator({
                                collection: f.postList
                            }),
                            f.postListHead.render(),
                            f.postList.render().reset(a),
                            f.paginator.render().reset(b),
                            f.replybox.postList = f.postList.el,
                            e.formPosition == "top" ? f.el.append(f.postListHead.el, f.postList.el, f.paginator.el) : (f.loginButtons || f.toolbar).before(f.postListHead.el, f.postList.el, f.paginator.el),
                            f.postListHead.reset(),
                            d(q.indicator()).appendTo(c.body),
                            d(q.poweredBy(k.site)).appendTo(f.el)
                    }
                },
                i.initEmbedThread = function (b) {
                    d.browser.msie && (b.addClass("ds-ie"), d.browser.version < 7 && b.addClass("ds-ie6"), d.browser.version < 9 && b.addClass("ds-ie8")),
                        j[0] = new m.EmbedThread(b),
                        j[0].render();
                    var e = [],
                        f = a.duoshuoQuery && duoshuoQuery.short_name ? "http://" + duoshuoQuery.short_name + "." + i.DOMAIN : i.REMOTE;
                    a.location.href && e.push("url=" + encodeURIComponent(a.location.href)),
                    c.referrer && e.push("referer=" + encodeURIComponent(c.referrer)),
                    g && e.push("user_agent=" + encodeURIComponent(g)),
                        d.each(["thread-id", "limit", "order", "title", "url"], function (a, c) {
                            b.data(c) && e.push(c.replace("-", "_") + "=" + encodeURIComponent(b.data(c)))
                        }),
                    a.duoshuoQuery && d.each(["local_identity", "signature"], function (a, b) {
                        duoshuoQuery[b] && e.push(b + "=" + encodeURIComponent(duoshuoQuery[b]))
                    }),
                        i.injectScript(f + "/thread.js?" + e.join("&"))
                },
                i.resetUnreadComments = function () {
                    var a = k.unreadComments ? k.unreadComments.length : 0;
                    j[0].el.find("a.ds-unread-comments-count").attr("title", a ? "你有" + a + "条新回复" : "你没有新回复").html(a).css("display", a ? "inline" : "none")
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